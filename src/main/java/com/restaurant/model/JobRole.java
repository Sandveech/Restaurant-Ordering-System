package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class JobRole {
    // fields
    private static int count = 0;
    private int id;
    private String title;

    // constructor
    public JobRole(String title) {
        setID(count++);
        setTitle(title);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Title: %s", id, title);
    }

    // getters and setters
    private int getCount() { return count; }
    private int getID() { return id; }
    public String getTitle() { return title; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    public void setTitle(String title) {
        this.title = (ValidationUtils.isValidText(title)) ? title : "No title";
    }
}
