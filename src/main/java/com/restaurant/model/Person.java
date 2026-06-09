package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.util.ValidationUtils;

abstract class Person {
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
    /**
     * Returns the first name of this person.
     * @return the first name of this person
     */
    public String getFirstName() { return first_name; }

    /**
     * Returns the last name of this person.
     * @return the last name of this person
     */
    public String getLastName() { return last_name; }

    /**
     * Returns the gender of this person.
     * @return the gender of this person
     */
    public String getGender() { return gender; }

    /**
     * Returns the email of this person.
     * @return the email of this person
     */
    public String getEmail() { return email; }

    /**
     * Returns the phone number of this person.
     * @return the phone number of this person
     */
    public String getPhoneNumber() { return phone_number; }

    /**
     * Returns the full name of this person.
     * @return the full name of this person
     */
    public String getFullName() { return first_name + " " + last_name; }

    /**
     * Sets the first name of this person
     * @param first_name the first name to set to
     */
    public void setFirstName(String first_name) {
        this.first_name = (ValidationUtils.isValidName(first_name)) ? first_name : "First Name";
    }

    /**
     * Sets the last name of this person
     * @param last_name the last name to set to
     */
    public void setLastName(String last_name) {
        this.last_name = (ValidationUtils.isValidName(last_name)) ? last_name : "Last Name";
    }

    /**
     * Sets the gender of this person
     * @param gender the gender to set to
     */
    public void setGender(String gender) {
        this.gender = (ValidationUtils.isValidText(gender)) ? gender : "Prefer not to say";
    }

    /**
     * Sets the email of this person.
     * @param email the email to set to
     */
    public void setEmail(String email) {
        this.email = (ValidationUtils.isValidEmail(email)) ? email : "No email";
    }

    /**
     * Sets the phone number of this person.
     * @param phone_number the phone number to set to
     */
    public void setPhoneNumber(String phone_number) {
        this.phone_number = (ValidationUtils.isValidPhoneNumber(phone_number)) ? phone_number : "No phone number";
    }
}
