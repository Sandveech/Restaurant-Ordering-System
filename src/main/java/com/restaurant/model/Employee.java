package src.main.java.com.restaurant.model;

import java.util.TreeSet;

import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.enums.Action;
import src.main.java.com.restaurant.interfaces.Activatable;
import src.main.java.com.restaurant.interfaces.Displayable;
import src.main.java.com.restaurant.util.ValidationUtils;

public abstract class Employee extends Person implements Displayable, Activatable {
    // fields
    protected static int employeeCount = 0;
    protected int id;
    protected boolean active = true;
    protected double salary;
    protected Employee createdBy;
    protected String username;
    protected String password;
    protected TreeSet<Action> permissions = new TreeSet<>();

    // constructor
    public Employee(String firstName, String lastName, String gender, String email, String phoneNumber, double salary, String username, String password, Employee createdBy) {
        super(firstName, lastName, gender, email, phoneNumber);
        setID(employeeCount++);
        setSalary(salary);
        setUsername(username);
        setPassword(password);
        setCreatedBy(createdBy);
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
    private static int getEmployeeCount() { return employeeCount; }

    /**
     * Returns the id of this employee.
     * @return the id of this employee
     */
    public int getID() { return id; }

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
    public Employee getCreatedBy() { return createdBy; }

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
        if (ValidationUtils.isValidID(id)) {
            this.id = id;
        }
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
    public void setSalary(double salary) {
        double min_salary = RestaurantConfig.getInstance().getMinSalary();
        this.salary = (salary >= min_salary) ? salary : min_salary;
    }

    /**
     * Sets the employee that created this employee.
     * @param employee the employee that created this employee
     */
    private void setCreatedBy(Employee employee) {
        if (employee == null) { return; }
        this.createdBy = employee;
    }
    
    /**
     * Sets the usrename of this employee.
     * @param username the username to set to
     */
    public void setUsername(String username) {
        if (!ValidationUtils.isValidUsername(username)) { return; }
        this.username = username;
    }

    /**
     * Sets the passsword of this employee.
     * @param password the passsword to set to
     */
    public void setPassword(String password) {
        if (!ValidationUtils.isValidPassword(password)) { return; }
        this.password = password;
    }

    /**
     * Displays this employee.
     */
    @Override
    public void display() {
        System.out.println("- " + getFullName());
        System.out.println("    + ID: " + id);
        System.out.println("    + Active?: " + active);
        System.out.println("    + Email:" + email);
        System.out.println("    + Phone Number: " + phoneNumber);
        System.out.println("    + Gender: " + gender);
        System.out.println("    + Username: " + username);
        work();
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

    /**
     * Returns a value indicating whether this employee has the specified permission.
     * @param permission
     */
    public boolean hasPermission(Action permission) {
        return permissions.contains(permission);
    }

    abstract void work();
}