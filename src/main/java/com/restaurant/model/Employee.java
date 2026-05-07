package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Employee extends Person implements Displayable {
    // fields
    private static int count = 1;
    private int id;
    private JobRole job_role;

    // constructor
    public Employee(String first_name, String last_name, String gender, String email, String phone_number, JobRole job_role) {
        super(first_name, last_name, gender, email, phone_number);
        setID(count++);
        setJobRole(job_role);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Gender: %s, Email: %s, Phone Number: %s, Job Role: %s", getFullName(), getGender(), getEmail(), getPhoneNumber(), getJobRole().getTitle());
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
     * Returns the job role of this employee.
     * @return the job role of this employee
     */
    public JobRole getJobRole() { return job_role; }

    /**
     * Sets the id of this employee.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the job role of this employee.
     * @param job_role the job role to set to
     */
    public void setJobRole(JobRole job_role) {
        if (job_role != null) { this.job_role = job_role; }
    }

    /**
     * Displays this employee.
     */
    public void display() {
        String title = (job_role != null) ? job_role.getTitle() : "Unknown title";

        System.out.println(String.format("Full Name: %s, Job Title: %s, Email: %s, Phone Number: %s", getFullName(), title, getEmail(), getPhoneNumber()));
    }
}