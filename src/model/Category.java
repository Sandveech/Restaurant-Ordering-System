package src.model;

import src.config.AppConstants;
import src.util.ValidationUtils;

public class Category {
    // fields
    private static int count = 0;
    private int id;
    private String name;
    private String description;

    // constructor
    public Category(String name, String decription) {
        setID(count++);
        setName(name);
        setDescription(decription);
    }

    // getters and setters
    private int getCount() { return count; }
    private int getID() { return id; }
    public String getName() { return name; }
    public String description() { return description; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    private void setName(String name) {
        this.name = (ValidationUtils.isValidName(name)) ? name : "Unknown Menu Item";
    }

    private void setDescription(String description) {
        this.description = (ValidationUtils.isValidText(description)) ? description : "No description.";
    } 
}   
