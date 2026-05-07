package src.main.java.com.restaurant.model;

import java.util.ArrayList;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class TableOrder implements Displayable, Calculatable {
    // fields
    private static int count = 0;
    private int id;
    private ArrayList<OrderItem> orders = new ArrayList<OrderItem>();
    private Employee waiter;
    private Table table;
    private Boolean complete = false;

    // constructor
    public TableOrder(Employee waiter, Table table) {
        setID(count++);
        setWaiter(waiter);
        setTable(table);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Waiter: %s, Table: %d", id, getWaiter().getFullName(), getTable().getNumber());
    }

    // getters and setters
    /**
     * Returns the historical count of table orders.
     * @return the historical count of table orders
     */
    private int getCount() { return count; }

    /**
     * Returns the id of this table order.
     * @return the id of this table order
     */
    private int getID() { return id; }

    /**
     * Returns the list of orders of this table order.
     * @return the list of orders of this table order
     */
    public ArrayList<OrderItem> getOrders() { return orders; }

    /**
     * Returns the waiter who took the order of this table.
     * @return the waiter who took the order of this table
     */
    public Employee getWaiter() { return waiter; }
    
    /**
     * Returns the table of this table order.
     * @return the table of this table order
     */
    public Table getTable() { return table; }

    /**
     * Returns {@true} if this table order is complete; otherwise, {@false}.
     * @return {@true} if this table order is complete; otherwise, {@false}
     */
    public Boolean isComplete() { return complete; }

    /**
     * Returns the total quantity of every order in this orders list
     * @param id
     */
    public int getOrderQuantities() {
        int count = 0;
        for (OrderItem o : orders) {
            if (o == null) { continue; }
            count += o.getQuantity();
        }

        return count;
    }

    /**
    * Sets the ID of this table order.
    * @param id the id to set to
    */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the waiter of this table order.
     * @param waiter the watieer to set to
     */
    public void setWaiter(Employee waiter) {
        if (waiter != null) { this.waiter = waiter; }
    }

    /**
     * Sets the table of this table order.
     * @param table the table to set to
     */
    public void setTable(Table table) {
        if (table != null) { this.table = table; }
    }

    /**
     * Sets the completion status of this table order.
     * @param complete {@true} if this table order is complete; otherwise, {@false}
     */
    private void setComplete(Boolean complete) {
        this.complete = complete;
    }

    // logic
    /**
     * Returns {@true} if the specified item of the specified quantity can be ordered; otherwise, {@false}
     * @param item the item to order
     * @param size the size label of of item
     * @param quantity the quanity of the order
     * @return {@true} if the specified item of the specified quantity can be ordered; otherwise, {@false}
     */
    private Boolean canOrder(MenuItem item, int quantity, String size) {
        if (quantity <= 0) { return false; }
        if (getOrderQuantities() + quantity > RestaurantConfig.getInstance().getMaxOrders()) { return false; }
        if (item == null) { return false; }
        if (!item.isActive()) { return false; }
        if (!item.priceOptionExists(size)) { return false; }

        return true;
    }

    /**
     * Returns the index of the order item within this list of orders. -1 is returned if the order item  of the specified size is not found.
     * @param item the menu item to search for 
     * @param size the size of the order item
     * @return the index of the order item wihtin this list of orders. -1 is returned if the menu item of the specified size is not found
     */
    private int searchOrder(MenuItem item, String size) {
        if (item == null) { return -1; }

        for (int i = 0; i < orders.size(); i++) {
            OrderItem o = orders.get(i);
            if (o == null) { continue; }

            MenuItem m = o.getItem();
            if (m == null) { continue; }

            if (m.getID() == item.getID() && o.getSize() == size) { return i; }
        }

        return -1;
    }

    /**
     * Adds a new order to this list of orders. Returns {@true} if the order was successfully added; otherwise, {@false}. 
     * @param item the menu item to add
     * @param quantity the quantity of the item
     * @param size the size of the item
     * @return {@true} if the order was successfully added; otherwise, {@false}
     */
    public Boolean addOrder(MenuItem item, int quantity, String size) {
        if (canOrder(item, quantity, size)) {
            int order_index = searchOrder(item, size);
            
            if (order_index != -1) {
                OrderItem order = orders.get(order_index);
                order.setQuantity(order.getQuantity() + quantity);
            }
            else {
                OrderItem order = new OrderItem(item, quantity, size);
                orders.add(order);
            }

            return true;
        }

        return false;
    }

    /**
     * Removes the order item of the specified quantity and size. Returns {@true} if the order was successfully removed; otherwise, {@false}.
     * @param item the menu item to remove
     * @param quantity the quantity of the item
     * @param size the size of the item
     * @return {@true} if the order was successfully removed; otherwise {@false}
     */
    public Boolean removeOrder(MenuItem item, int quantity, String size) {
        if (orders.isEmpty()) { return false; }

        if (canOrder(item, quantity, size)) {
            int order_index = searchOrder(item, size);

            if (order_index != -1) { return false; }

            OrderItem o = orders.get(order_index);

            if (quantity >= o.getQuantity()) {
                orders.remove(order_index); }
            else {
                o.setQuantity(o.getQuantity() - quantity);
            }

            return true;
        }

        return false;
    }

    /**
     * Calculates and returns the subtotal price
     * @return the subtotal price
     */
    public double calculateSubtotalPrice() {
        double subtotal = 0;
        for (OrderItem o : orders) {
            if (o == null) { continue; }
            subtotal += o.calculateSubtotalPrice();
        }

        return subtotal;
    }

    /**
     * Calculates and returns the tax amount of this order item with the specified tax percentage.
     * @param tax_percentage the tax percentage to calculate with
     * @return the tax amount of this order item with the specified tax percentage
     */
    public double calculateTaxAmount(double tax_percentage) {
        return calculateSubtotalPrice() * (tax_percentage / 100);
    }
    
    /**
     * Calculates and returns the total price of this order item with the specified tax percentage.
     * @param tax_percentage the tax percentage to calculate with
     * @return the total price of this order item with the specified tax percentage
     */
    public double calculateTotalPrice(double tax_percentage) {
        double total = calculateSubtotalPrice();
        return total + (total * (tax_percentage / 100))
    }

    /**
     * Displays all the orders in this orders list.
     */
    public void display() {
        for (int i = 0; i < orders.size(); i++) {
            OrderItem o = orders.get(i);
            if (o == null) { continue; }

            String item_name = (o.getItem() != null) ? o.getItem().getName() : "Unknown item";

            System.out.println(String.format("%-32s %s %12.2f", (i + 1) + " " + item_name + " (" + o.getSize() + ")", o.getQuantity() + "x", o.calculateSubtotalPrice()));
        }
    }
}
