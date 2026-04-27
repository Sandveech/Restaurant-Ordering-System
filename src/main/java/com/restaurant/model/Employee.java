package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Employee extends Person {
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
        return String.format("Name: %s, Gender: %s, Email: %s, Phone Number: %s, Job Role: %s", getFullName(), gender, email, phone_number, getJobRole().getTitle());
    }

    // getters and setters
    private int getCount() { return count; }
    private int getID() { return id; }
    public JobRole getJobRole() { return job_role; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    public void setJobRole(JobRole job_role) {
        if (job_role != null) { this.job_role = job_role; }
    }
    
}