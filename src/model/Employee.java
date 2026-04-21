package src.model;

import src.Utilities.NameUtils;

public class Employee {
    private static int count = 0;
    private int id;
    private String first_name;
    private String last_name;

    public Employee(String first_name, String last_name) {
        setId(count++);
        setFirstName(first_name);
        setLastName(last_name);
    }

    // setters
    private void setId(int id) {
        if (id >= 0) { this.id = id; }
    } 

    public void setFirstName(String first_name) {
        this.first_name = (NameUtils.isValidName(first_name)) ? first_name : NameUtils.DEFAULT_NAME;    
    }

    public void setLastName(String last_name) {
        this.last_name = (NameUtils.isValidName(last_name)) ? last_name : NameUtils.DEFAULT_NAME;
    }

    // getters
    private int getCount() {
        return count;
    }

    private int getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    // utilities
}
