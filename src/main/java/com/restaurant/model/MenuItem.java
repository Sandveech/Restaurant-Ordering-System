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
    private int getCount() { return count; }
    public int getID() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public double getPrice() { return price; }
    public Boolean isActive() { return active; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    public void setName(String name) {
        this.name = (ValidationUtils.isValidName(name)) ? name : "Unnamed Item";
    }

    public void setDescription(String description) {
        this.description = (ValidationUtils.isValidText(description)) ? description : "No description.";
    }

    public void setCategory(Category category) {
        if (category != null) { this.category = category; }
    }

    public void setPrice(double price) {
        this.price = (ValidationUtils.isValidPrice(price)) ? price : RestaurantConfig.getInstance().getMinPrice();
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
