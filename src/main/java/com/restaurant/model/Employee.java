package src.main.java.com.restaurant.model;

import java.util.TreeSet;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.enums.Permission;
import src.main.java.com.restaurant.interfaces.Activatable;
import src.main.java.com.restaurant.interfaces.Displayable;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Employee extends Person implements Displayable, Activatable {
    // fields
    private static int employee_count = 0;
    private int id;
    private boolean active = true;
    private double salary;
    private Employee created_by;
    private String username;
    private String password;
    protected TreeSet<Permission> permissions = new TreeSet<>();

    // constructor
    public Employee(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password) {
        super(first_name, last_name, gender, email, phone_number);
        setID(employee_count++);
        setSalary(salary);
        setUsername(username);
        setPassword(password);
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
    public boolean isActive() { return active; }

    /**
     * Returns the salary of this employee.
     * @return the salary of this employee
     */
    public double getSalary() { return salary; }

    /**
     * Returns the employee who created this employee.
     * @return the employee who created this employee
     */
    public Employee getCreatedBy() { return created_by; }

    /**
     * Returns the username of this employee.
     * @return the username of this employee
     */
    public String getUsername() { return username; }

    /**
     * Returns the password of this employee.
     * @return the password of this employee
     */
    public String getPassword() { return password; }

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
     * Sets the employee that created this employee.
     * @param employee the employee that created this employee
     */
    private void setCreatedBy(Employee employee) {
        if (employee == null) { return; }
        this.created_by = employee;
    }
    
    /**
     * Sets the usrename of this employee.
     * @param username the username to set to
     * @return {@true} if and only if the username is succesfully set; otherwise, {@false}
     */
    private boolean setUsername(String username) {
        if (!ValidationUtils.isValidUsername(username)) { return false; }
        this.username = username;
        return true;
    }

    /**
     * Sets the passsword of this employee.
     * @param password the passsword to set to
     * @return {@true} if and only if the password is succesfully set; otheriwse, {@false}
     */
    private boolean setPassword(String password) {
        if (!ValidationUtils.isValidPassword(password)) { return false; }
        this.password = password;
        return true;
    }

    /**
     * Displays this employee.
     */
    @Override
    public void display() {
        System.out.println(String.format("Full Name: %s, Email: %s, Phone Number: %s", getFullName(), getEmail(), getPhoneNumber()));
    }

    /**
     * Activates this employee.
     */
    @Override
    public void activate() {
        this.active = true;
    }

    /**
     * Deactivates this employee
     */
    @Override
    public void deactivate() {
        this.deactivate();
    }

    public void work() {
        System.out.println(getFullName() + " is working in the restaurant.");
    }

    /**
     * Returns a value indicating whether this employee has the specified permission.
     * @param permission
     */
    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}