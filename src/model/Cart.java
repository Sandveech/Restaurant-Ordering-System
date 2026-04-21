package src.model;
import java.util.ArrayList;

public class Cart {
    private ArrayList<OrderItem> orders;
    private int min_orders;
    private int max_orders;

    public Cart(int min_orders, int max_orders) {
        setMinOrders(min_orders).setMaxOrders(max_orders);
        orders = new ArrayList<>();
    }

    // setters
    private Cart setMinOrders(int n) {
        if (n >= 0) { this.min_orders = n; }
        return this;
    }

    private Cart setMaxOrders(int n) {
        this.max_orders = (n >= min_orders) ? n : min_orders;
        return this;
    }

    // getters
    public int getMinOrders() {
        return min_orders;
    }

    public int getMaxOrders() {
        return max_orders;
    }

    public ArrayList<OrderItem> getOrders() {
        return orders;
    }
    
    public int getOrderCount() {
        return orders.size();
    }

    // utilities
    public Cart addOrder(OrderItem order) {
        if (getOrderCount() < getMaxOrders()) { orders.add(order); }
        return this;
    }

    public Cart removeOrder(int orderId, int quantity) {
        if (getOrderCount() > 0 && quantity > 0) {
            for (int i = 0; i < getOrderCount(); i++) {
                OrderItem order = getOrders().get(i);
                if (order.getId() == orderId) {
                    order.setQuantity(order.getQuantity() - quantity);
                    if (order.getQuantity() <= 0) { getOrders().remove(i); }
                    break;
                }
            }
        }

        return this;
    }

    public double calculateTotalCost(double tax_percentage) {
        double raw_total = 0;
        for (OrderItem order : orders) {
            raw_total += order.calculateTotalCost();
        }
        return raw_total + (raw_total * tax_percentage);
    }

    public Cart printOrders() {
        for (OrderItem order : orders) {
            MenuItem item = order.getItem();
            System.out.println("Name: " + item.getName() + ", Quantity: " + order.getQuantity() + "x" + ", Total: $" + order.calculateTotalCost());
        }

        return this;
    }
}
