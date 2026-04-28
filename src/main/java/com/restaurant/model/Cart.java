package src.main.java.com.restaurant.model;

import java.util.ArrayList;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Cart {
    // fields
    private static int count = 0;
    private int id;
    private ArrayList<Order> orders = new ArrayList<Order>();
    private int order_count;
    private Table table;

    // constructor
    public Cart(Table table) {
        setID(count++);
        setTable(table);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Table: %d", id, getTable().getNumber());
    }

    // getters and setters
    private int getCount() { return count; }
    private int getID() { return id; }
    public ArrayList<Order> getOrders() { return orders; }
    private int getOrderCount() { return order_count; }
    public Table getTable() { return table; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    private void setOrderCount(int n) {
        this.order_count = (n >= 0) ? n : 0;
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
            Order order = orders.get(i);
            if (order == null) { continue; }

            MenuItem order_item = order.getItem();
            if (order_item == null) { continue; }

            if (order_item.getID() == item.getID() && order.getSize() == size) { return i; }
        }

        return -1;
    }

    public Boolean addOrder(MenuItem item, int quantity, String size, Employee waiter) {
        if (canOrder(item, quantity)) {
            int order_index = searchOrder(item, size);
            
            if (order_index != -1) {
                Order order = orders.get(order_index);
                order.setQuantity(order.getQuantity() + quantity);
            }
            else {
                Order order = new Order(item, quantity, size, this, waiter);
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

            Order order = orders.get(order_index);

            if (quantity >= order.getQuantity()) {
                orders.remove(order_index); }
            else {
                order.setQuantity(order.getQuantity() - quantity);
            }

            return true;
        }

        return false;
    }
}
