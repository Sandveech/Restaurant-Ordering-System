package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.util.ValidationUtils;

public class Person {
    // fields
    private String first_name;
    private String last_name;
    private String gender;
    private String email;
    private String phone_number;

    // constructor
    public Person(String first_name, String last_name, String gender, String email, String phone_number) {
        setFirstName(first_name);
        setLastName(last_name);
        setGender(gender);
        setEmail(email);
        setPhoneNumber(phone_number);
    }

    @Override
    public String toString() {
        return String.format("First Name: %s, Last Name: %s, Gender: %s, Email: %s, Phone Number: %s", first_name, last_name, gender, email, phone_number);
    }

    // getters and setters
    public String getFirstName() { return first_name; }
    public String getLastName() { return last_name; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phone_number; }
    public String getFullName() { return first_name + " " + last_name; }

    public void setFirstName(String first_name) {
        this.first_name = (ValidationUtils.isValidName(first_name)) ? first_name : "First Name";
    }

    public void setLastName(String last_name) {
        this.last_name = (ValidationUtils.isValidName(last_name)) ? last_name : "Last Name";
    }

    public void setGender(String gender) {
        this.gender = (ValidationUtils.isValidText(gender)) ? gender : "Prefer not to say";
    }

    public void setEmail(String email) {
        this.email = (ValidationUtils.isValidEmail(email)) ? email : "No email";
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = (ValidationUtils.isValidPhoneNumber(phone_number)) ? phone_number : "No phone number";
    }
}
