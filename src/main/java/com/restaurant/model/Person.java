package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.util.ValidationUtils;

abstract class Person {
    // fields
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String email;
    protected String phoneNumber;

    // constructor
    public Person(String firstName, String lastName, String gender, String email, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("First Name: %s, Last Name: %s, Gender: %s, Email: %s, Phone Number: %s", firstName, lastName, gender, email, phoneNumber);
    }

    // getters and setters
    /**
     * Returns the first name of this person.
     * @return the first name of this person
     */
    public String getFirstName() { return firstName; }

    /**
     * Returns the last name of this person.
     * @return the last name of this person
     */
    public String getLastName() { return lastName; }

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
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Returns the full name of this person.
     * @return the full name of this person
     */
    public String getFullName() { return firstName + " " + lastName; }

    /**
     * Sets the first name of this person
     * @param firstName the first name to set to
     */
    public void setFirstName(String firstName) {
        this.firstName = (ValidationUtils.isValidName(firstName)) ? firstName : "First Name";
    }

    /**
     * Sets the last name of this person
     * @param lastName the last name to set to
     */
    public void setLastName(String lastName) {
        this.lastName = (ValidationUtils.isValidName(lastName)) ? lastName : "Last Name";
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
     * @param phoneNumber the phone number to set to
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (ValidationUtils.isValidPhoneNumber(phoneNumber)) ? phoneNumber : "No phone number";
    }
}
