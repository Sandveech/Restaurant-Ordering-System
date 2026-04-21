package src.model;
import java.util.ArrayList;

public class Cart {
    private int max_orders = 32;

    public Cart(int min_orders, int max_orders) {
        setMaxOrders(max_orders);
    }

    // setters
    private void setMaxOrders(int n) {
        if (n >= 1) { this.max_orders = n; }
    }

    // getters
    public int getMaxOrders() {
        return max_orders;
    }

    // utilities
}
