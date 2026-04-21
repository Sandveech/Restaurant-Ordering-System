package src.model;

public class Employee {
    private static int count = 0;
    private int id;
    private String first_name;
    private String last_name;

    public Employee(String first_name, String last_name) {
        setId(count++).setFirstName(first_name).setLastName(last_name);
    }

    // setters
    private Employee setId(int id) {
        if (id >= 0) { this.id = id; }
        return this; 
    } 

    public Employee setFirstName(String first_name) {
        this.first_name = (isValidName(first_name)) ? first_name : "Unknown";
        return this;    
    }

    public Employee setLastName(String last_name) {
        this.last_name = (isValidName(last_name)) ? last_name : "Unknown";
        return this;
    }

    // getters
    public int getCount() {
        return count;
    }

    public int getId() {
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
    private Boolean isValidName(String name) {
        return name != null && !name.isEmpty() && !name.isBlank();
    }
}
