package src.main.java.com.restaurant.model;

import java.util.ArrayList;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class TableOrder {
    // fields
    private static int count = 0;
    private int id;
    private ArrayList<OrderItem> orders = new ArrayList<OrderItem>();
    private int order_count;
    private Employee waiter;
    private Table table;
    private Receipt receipt;
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
     * Returns the total order count of this table order.
     * @return the total order count of this table order
     */
    private int getOrderCount() { return order_count; }

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
     * Returns the receipt of this table order.
     * @return the receipt of this table order
     */
    public Receipt getReceipt() { return receipt; }

    /**
     * Returns {@true} if this table order is complete; otherwise, {@false}.
     * @return {@true} if this table order is complete; otherwise, {@false}
     */
    public Boolean isComplete() { return complete; }

    /**
    * Sets the ID of this table order.
    * @param id the id to set to
    */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the order count of this table order.
     * @param n the order count to set to
     */
    private void setOrderCount(int n) {
        this.order_count = (n >= 0) ? n : 0;
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
     * Sets the receipt of this table order.
     * @param receipt the receipt to set to
     */
    private void setReceipt(Receipt receipt) {
        if (receipt != null) { this.receipt = receipt; }
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
     * @param quantity the quanity of the order
     * @return {@true} if the specified item of the specified quantity can be ordered; otherwise, {@false}
     */
    private Boolean canOrder(MenuItem item, int quantity) {
        if (quantity <= 0) { return false; }
        if (order_count + quantity > RestaurantConfig.getInstance().getMaxOrders()) { return false; }
        if (item == null) { return false; }
        if (!item.isActive()) { return false; }

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
        if (canOrder(item, quantity)) {
            int order_index = searchOrder(item, size);
            
            if (order_index != -1) {
                OrderItem order = orders.get(order_index);
                order.updateQuantity(order.getQuantity() + quantity);
            }
            else {
                OrderItem order = new OrderItem(item, quantity, size);
                orders.add(order);
            }

            order_count += quantity;
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

        if (canOrder(item, quantity)) {
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
     * Displays all the orders in this orders list.
     */
    public void displayOrders() {
        for (int i = 0; i < orders.size(); i++) {
            OrderItem o = orders.get(i);
            System.out.println(String.format("%d. %s | $%.2f", i + 1, o, o.calculateTotalPrice()));
        }
    }

    /**
     * Completes the order of this table order and returns a receipt
     * @return the new receipt
     */
    public Receipt completeOrder() {
        if (!isComplete()) {
            Receipt receipt = new Receipt(this, table, waiter, RestaurantConfig.getInstance().getTaxPercentage());
            setReceipt(receipt);
            setComplete(true);
            return receipt;
        }

        return this.receipt;
    }
}
