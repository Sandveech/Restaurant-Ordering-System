package src.main.java;
import java.util.ArrayList;

public class Cart {
    private ArrayList<OrderItem> orders;

    public Cart() {
        orders = new ArrayList<>();
    }

    // setters

    // getters
    public ArrayList<OrderItem> getOrders() {
        return orders;
    }
    
    public int getOrderCount() {
        return orders.size();
    }

    // public
    public Cart pushOrder(OrderItem order) {
        if (getOrderCount() < 10) { orders.add(order); }
        return this;
    }
}
