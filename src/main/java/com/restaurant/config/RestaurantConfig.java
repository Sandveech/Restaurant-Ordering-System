package src.main.java.com.restaurant.config;

import src.main.java.com.restaurant.util.ValidationUtils;

public class RestaurantConfig {
    // fields
    private static final RestaurantConfig INSTANCE = new RestaurantConfig();
    private String name;
    private String address;
    private String phone_number;
    private double tax_percentage;
    private double min_price;
    private int max_orders;
    private String receipt_message;
    private double min_salary;

    // constructor
    private RestaurantConfig() {
        setName("Unnamed Restaurant");
        setAddress("No address");
        setPhoneNumber("No Phone number");
        setTaxPercentage(10);
        setMinPrice(0);
        setMaxOrders(32);
        setReceiptMessage("Thank you for dining with us!");
        setMinSalary(300);
    };
    
    // getters and setters
    public static RestaurantConfig getInstance() { return INSTANCE; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phone_number; }
    public double getTaxPercentage() { return tax_percentage; }
    public double getMinPrice() { return min_price; }
    public int getMaxOrders() { return max_orders; }
    public String getReceiptMessage() { return receipt_message; }
    public double getMinSalary() { return min_salary; }

    public void setName(String name) {
        this.name = (ValidationUtils.isValidText(name)) ? name : "Unnamed Restaurant";
    }

    public void setAddress(String address) {
        this.address = (ValidationUtils.isValidText(address)) ? address : "No address";
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = (ValidationUtils.isValidPhoneNumber(phone_number)) ? phone_number : "No phone number";
    }

    public void setTaxPercentage(double tax_percentage) {
        this.tax_percentage = (tax_percentage >= 0) ? tax_percentage : 0;
    }

    public void setMinPrice(double price) {
        this.min_price = (price >= 0) ? price : 0;
    }

    public void setMaxOrders(int orders) {
        this.max_orders = (orders >= 1) ? orders : 1;
    }

    public void setReceiptMessage(String message) {
        this.receipt_message = (ValidationUtils.isValidText(message)) ? message : "";
    }

    public void setMinSalary(double salary) {
        this.min_salary = (salary >= 0) ? salary : 0;
    }
}
