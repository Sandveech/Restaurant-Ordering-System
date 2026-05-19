package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Category {
    // fields
    private static int category_count = 0;
    private int id;
    private String name;
    private String description;

    // constructor
    public Category(String name, String decription) {
        setID(category_count++);
        setName(name);
        setDescription(decription);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Description: %s", id, name, description);
    }

    // getters and setters
    /**
     * Returns the historical count of categories.
     * @return the historical count of categories
     */
    private static int getCategoryCount() { return category_count; }

    /**
     * Returns the id of this category.
     * @return the id of this category
     */
    private int getID() { return id; }

    /**
     * Returns the name of this category.
     * @return the name of this category
     */
    public String getName() { return name; }

    /**
     * Returns the description of this category.
     * @return the description of this category
     */
    public String description() { return description; }

    /**
     * Sets the id of this category.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the nae of this category.
     * @param name the name to set to
     */
    private void setName(String name) {
        this.name = (ValidationUtils.isValidName(name)) ? name : "Unknown Menu Item";
    }

    /**
     * Sets the description of this category.
     * @param description the descripttion to set to
     */
    private void setDescription(String description) {
        this.description = (ValidationUtils.isValidText(description)) ? description : "No description.";
    } 
}   
