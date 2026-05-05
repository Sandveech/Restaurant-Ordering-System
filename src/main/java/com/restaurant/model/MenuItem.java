package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class MenuItem {
    // fields
    private static int count = 0;
    private int id;
    private String name;
    private String description;
    private Category category;
    private double price;
    private Boolean active;

    // constructor
    public MenuItem(String name, String description, Category category, double price, Boolean active) {
        setID(count++);
        setName(name);
        setDescription(description);
        setCategory(category);
        setPrice(price);
        setActive(active);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Description: %s, Category: %s, Price: $%.2f, Active: %b", name, description, getCategory().getName(), price, active);
    }

    // getters and setters
    /**
     * Returns the historical count of menu items.
     * @return the historical count of menu items
     */
    private int getCount() { return count; }

    /**
     * Returns the id of this menu item.
     * @return the id of this menu item
     */
    public int getID() { return id; }

    /**
     * Returns the name of this menu item.
     * @return the name of this menu item
     */
    public String getName() { return name; }

    /**
     * Returns the description of this menu item.
     * @return the description of this menu item
     */
    public String getDescription() { return description; }

    /**
     * Returns the category of this menu item.
     * @return the category of this menu item
     */
    public Category getCategory() { return category; }

    /**
     * Returns the price of this menu item.
     * @return the price of this menu item
     */
    public double getPrice() { return price; }

    /**
     * Returns {@true} if this menu item is active; otherwise, {@false}
     * @return {@true} if this menu item is active; otherwise, {@false}
     */
    public Boolean isActive() { return active; }

    /**
     * Sets the id of this menu item.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the name of this menu item.
     * @param name the name to set to
     */
    public void setName(String name) {
        this.name = (ValidationUtils.isValidName(name)) ? name : "Unnamed Item";
    }


    /**
     * Sets the description of this menu item.
     * @param description the description to set to
     */
    public void setDescription(String description) {
        this.description = (ValidationUtils.isValidText(description)) ? description : "No description.";
    }

    /**
     * Sets the category of this menu item.
     * @param category the category to set to
     */
    public void setCategory(Category category) {
        if (category != null) { this.category = category; }
    }

    /**
     * Sets the price of this menu item.
     * @param price the price to set to
     */
    public void setPrice(double price) {
        this.price = (ValidationUtils.isValidPrice(price)) ? price : RestaurantConfig.getInstance().getMinPrice();
    }

    /**
     * Sets the active state of this menu item.
     * @param active {@true} for this menu item to be active; otherwise, {@false}
     */
    public void setActive(Boolean active) {
        this.active = active;
    }
}
