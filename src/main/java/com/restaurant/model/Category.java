package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.interfaces.Displayable;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Category implements Displayable {
    // fields
    private static int categoryCount = 0;
    private int id;
    private String name;
    private String description;

    // constructor
    public Category(String name, String decription) {
        setID(categoryCount++);
        setName(name);
        setDescription(decription);
    }

    public Category(String name) {
        setID(categoryCount++);
        setName(name);
        setDescription("No description.");
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
    private static int getCategoryCount() { return categoryCount; }

    /**
     * Returns the id of this category.
     * @return the id of this category
     */
    public int getID() { return id; }

    /**
     * Returns the name of this category.
     * @return the name of this category
     */
    public String getName() { return name; }

    /**
     * Returns the description of this category.
     * @return the description of this category
     */
    public String getDescription() { return description; }

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
    public void setName(String name) {
        this.name = (ValidationUtils.isValidText(name)) ? name : "Unknown Menu Item";
    }

    /**
     * Sets the description of this category.
     * @param description the descripttion to set to
     */
    public void setDescription(String description) {
        this.description = (ValidationUtils.isValidText(description)) ? description : "No description.";
    } 

    /**
     * Displays the information of this category.
     */
    @Override
    public void display() {
        System.out.println("- " + name);
        System.out.println("    + ID: " + id);
        System.out.println("    + Description: " + description);
    }
}   
