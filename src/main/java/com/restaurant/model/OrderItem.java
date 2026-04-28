package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class OrderItem {
    // fields
    private static int count = 0;
    private int id;
    private MenuItem item;
    private int quantity;
    private String size;

    // constructor
    public OrderItem(MenuItem item, int quantity, String size) {
        setID(count++);
        setItem(item);
        setQuantity(quantity);
        setSize(size);
    }

    @Override
    public String toString() {
        return String.format("Item: %s, Size: %s, Quantity: %dx", getItem().getName(), size, quantity);
    }

    // getters and setters
    private int getCount() { return count; }
    private int getID() { return id; }
    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public String getSize() { return size; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    private void setItem(MenuItem item) {
        if (item != null) { this.item = item; }
    }

    public void setQuantity(int n) {
        this.quantity = (n >= 0) ? n : 0;
    }

    public void setSize(String size) {
        this.size = (ValidationUtils.isValidText(size)) ? size : "Normal";
    }

    public double calculateTotalPrice() {
        return (item != null) ? item.getPrice() * quantity : 0;
    }
}
