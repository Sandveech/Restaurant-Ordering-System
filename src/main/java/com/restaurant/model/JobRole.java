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
    /**
     * Returns the historical count of job roles.
     * @return the historical count of job roles
     */
    private int getCount() { return count; }
    
    /**
     * Returns the id of this job role.
     * @return the id of this job role
     */
    private int getID() { return id; }


    /**
     * Returns the title of this job role.
     * @return the title of this job role
     */
    public String getTitle() { return title; }

    /**
     * Sets the id of this job role.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the title of this job role.
     * @param title the title to set to
     */
    public void setTitle(String title) {
        this.title = (ValidationUtils.isValidText(title)) ? title : "No title";
    }
}
