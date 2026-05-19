package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Employee extends Person implements Displayable {
    // fields
    private static int employee_count = 0;
    private int id;
    private Boolean active = true;
    private double salary;

    // constructor
    public Employee(String first_name, String last_name, String gender, String email, String phone_number, double salary) {
        super(first_name, last_name, gender, email, phone_number);
        setID(employee_count++);
        setSalary(salary);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Gender: %s, Email: %s, Phone Number: %s", getFullName(), getGender(), getEmail(), getPhoneNumber());
    }

    // getters and setters
    /**
     * Returns the historical count of employees.
     * @return the historical count of employees
     */
    private static int getEmployeeCount() { return employee_count; }

    /**
     * Returns the id of this employee.
     * @return the id of this employee
     */
    private int getID() { return id; }

    /**
     * Returns the value indicating whether this employee is active.
     * @return 
     */
    public Boolean isActive() { return active; }

    /**
     * Returns the salary of this employee.
     * @return the salary of this employee
     */
    public double getSalary() { return salary; }

    /**
     * Sets the id of this employee.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the active state of this employee.
     * @param active {@true} to be active; otherwise, {@false}
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Sets the salary of this employee.
     * @param salary the salary value
     */
    private void setSalary(double salary) {
        double min_salary = RestaurantConfig.getInstance().getMinSalary();
        this.salary = (salary >= min_salary) ? salary : min_salary;
    }

    /**
     * Displays this employee.
     */
    @Override
    public void display() {
        System.out.println(String.format("Full Name: %s, Email: %s, Phone Number: %s", getFullName(), getEmail(), getPhoneNumber()));
    }

    public void work() {
        System.out.println(getFullName() + " is working in the restaurant.");
    }
}