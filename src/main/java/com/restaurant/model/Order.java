package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Order {
    // fields
    private static int count = 0;
    private int id;
    private MenuItem item;
    private int quantity;
    private String size;
    private Cart cart;

    // constructor
    public Order(MenuItem item, int quantity, String size, Cart cart) {
        setID(count++);
        setItem(item);
        setQuantity(quantity);
        setSize(size);
        setCart(cart);
    }

    @Override
    public String toString() {
        return String.format("Item: %s, Size: %s, Quantity: %dx", getItem().getName(), getSize(), getQuantity());
    }

    // getters and setters
    private int getCount() { return count; }
    private int getID() { return id; }
    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public String getSize() { return size; }
    public Cart getCart() { return cart; }

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
        this.size = (ValidationUtils.isValidText(size)) ? size : AppConstants.DEFAULT_DISH_SIZE;
    }

    private void setCart(Cart cart) {
        if (cart != null) { this.cart = cart; }
    }

    public double calculateTotalPrice() {
        return (item != null) ? item.getPrice() * quantity : 0;
    }
}
