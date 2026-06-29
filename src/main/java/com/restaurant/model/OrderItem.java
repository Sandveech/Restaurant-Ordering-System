package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.interfaces.Calculatable;
import src.main.java.com.restaurant.interfaces.Displayable;
import src.main.java.com.restaurant.util.ValidationUtils;

public class OrderItem implements Displayable, Calculatable {
    // fields
    private static int orderItemCount = 0;
    private int id;
    private MenuItem item;
    private int quantity;
    private String size;
    private double unitPrice;

    // constructor
    public OrderItem(MenuItem item, int quantity, String size) {
        setID(orderItemCount++);
        setItem(item);
        setQuantity(quantity);
        setSize(size);

        if (item != null) { setUnitPrice(item.priceOfSize(size)); }
        else { setUnitPrice(RestaurantConfig.getInstance().getMinPrice()); }
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
    private static int getOrderItemCount() { return orderItemCount; }

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
     * Returns the unit price of this order item.
     * @return the unit price of this order item
     */
    public double getUnitPrice() { return unitPrice; }

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
     * Sets the unit price of this order item.
     * @param price the unit price to set to
     */
    private void setUnitPrice(double price) {
        this.unitPrice = (price < 0) ? RestaurantConfig.getInstance().getMinPrice() : price;
    }

    /**
     * Calculates and returns the subtotal price of this order item.
     * @return the subtotal price of this order item
     */
    @Override
    public double calculate() {
        if (item == null) { return 0; }
        return item.priceOfSize(size) * quantity;
    }

    /**
     * Displays this order item
     */
    @Override
    public void display() {
        String name = (item == null) ? "Unknown Item" : item.getName();
        double unitPrice = (item == null) ? 0 : item.priceOfSize(size);

        System.out.println(String.format("Name: %s, Size: %s, Unit Price: %.2f", name, size, unitPrice));
    }
}
