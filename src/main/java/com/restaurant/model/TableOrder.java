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
    private int getCount() { return count; }
    private int getID() { return id; }
    public ArrayList<OrderItem> getOrders() { return orders; }
    private int getOrderCount() { return order_count; }
    public Employee getWaiter() { return waiter; }
    public Table getTable() { return table; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    private void setOrderCount(int n) {
        this.order_count = (n >= 0) ? n : 0;
    }

    public void setWaiter(Employee waiter) {
        if (waiter != null) { this.waiter = waiter; }
    }

    public void setTable(Table table) {
        if (table != null) { this.table = table; }
    }

    // logic
    private Boolean canOrder(MenuItem item, int quantity) {
        if (quantity <= 0) { return false; }
        if (order_count + quantity > RestaurantConfig.getInstance().getMaxOrders()) { return false; }
        if (item == null) { return false; }
        if (!item.isActive()) { return false; }

        return true;
    }

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

    public Boolean addOrder(MenuItem item, int quantity, String size) {
        if (canOrder(item, quantity)) {
            int order_index = searchOrder(item, size);
            
            if (order_index != -1) {
                OrderItem order = orders.get(order_index);
                order.setQuantity(order.getQuantity() + quantity);
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

    public void displayOrders() {
        for (int i = 0; i < orders.size(); i++) {
            OrderItem o = orders.get(i);
            System.out.println(String.format("%d. %s | $%.2f", i + 1, o, o.calculateTotalPrice()));
        }
    }
}
