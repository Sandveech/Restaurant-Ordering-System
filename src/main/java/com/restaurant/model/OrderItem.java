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
    private double subtotal;

    // constructor
    public OrderItem(MenuItem item, int quantity, String size) {
        setID(count++);
        setItem(item);
        setQuantity(quantity);
        setSize(size);
        setSubtotal(calculateTotalPrice());
    }

    @Override
    public String toString() {
        return String.format("Item: %s, Size: %s, Quantity: %dx", getItem().getName(), size, quantity);
    }

    // getters and setters
    /**
     * Returns the historical count of order items.
     * @return the historical count of order items
     */
    private int getCount() { return count; }

    /**
     * Returns the id of this order item.
     * @return the id of this order item
     */
    private int getID() { return id; }
    
    /**
     * Returns the menu item of this order item.
     * @return the menu item of this order item
     */
    public MenuItem getItem() { return item; }

    /**
     * Sets the quantity of this order item.
     * @return the quantity of this order item
     */
    public int getQuantity() { return quantity; }

    /**
     * Returns the size of this order item.
     * @return the size of this order item
     */
    public String getSize() { return size; }

    /**
     * Returns the subtotal of this order item.
     * @return the subtotal of this order item
     */
    public double getSubtotal() { return subtotal; }

    /**
     * Sets the id of this order item.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the menu item of this order item.
     * @param item the menu itemm to set to
     */
    private void setItem(MenuItem item) {
        if (item != null) { this.item = item; }
    }

    /**
     * Sets the quantity of this order item.
     * @param n the quantity to set to
     */
    public void setQuantity(int n) {
        this.quantity = (n >= 0) ? n : 0;
    }

    /**
     * Sets the size of this order item.
     * @param size the size to set to
     */
    public void setSize(String size) {
        this.size = (ValidationUtils.isValidText(size)) ? size : "Normal";
    }

    /**
     * Sets the subtotal of this order item
     * @param subtotal the subtotal to set to
     */
    private void setSubtotal(double subtotal) {
        this.subtotal = (subtotal < 0) ? 0 : subtotal;
    }

    /**
     * Calculates and returns the total price of this order item.
     * @return the total price of this order item
     */
    public double calculateTotalPrice() {
        return (item != null) ? item.getPrice() * quantity : 0;
    }

    /**
     * Updates the quantity and subtotal of this order item.
     * @param count the quantity to update to
     */
    public void updateQuantity(int count) {
        setQuantity(count);
        setSubtotal(calculateTotalPrice());
    }
}
