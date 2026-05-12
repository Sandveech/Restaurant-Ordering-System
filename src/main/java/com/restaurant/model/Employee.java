package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Employee extends Person implements Displayable {
    // fields
    private static int count = 1;
    private int id;

    // constructor
    public Employee(String first_name, String last_name, String gender, String email, String phone_number) {
        super(first_name, last_name, gender, email, phone_number);
        setID(count++);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Gender: %s, Email: %s, Phone Number: %s, Job Role: %s", getFullName(), getGender(), getEmail(), getPhoneNumber());
    }

    // getters and setters
    /**
     * Returns the historical count of employees.
     * @return the historical count of employees
     */
    private int getCount() { return count; }

    /**
     * Returns the id of this employee.
     * @return the id of this employee
     */
    private int getID() { return id; }

    /**
     * Sets the id of this employee.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Displays this employee.
     */
    public void display() {
        System.out.println(String.format("Full Name: %s, Job Title: %s, Email: %s, Phone Number: %s", getFullName(), getEmail(), getPhoneNumber()));
    }
}