package src.main.java.com.restaurant.config;

import src.main.java.com.restaurant.util.ValidationUtils;

public class RestaurantConfig {
    // fields
    private static final RestaurantConfig INSTANCE = new RestaurantConfig();
    private String name;
    private String address;
    private String phoneNumber;
    private double taxPercentage;
    private double minPrice;
    private int maxOrders;
    private String receiptMessage;
    private double minSalary;
    private String defaultPaymentMethod;

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
    public String getPhoneNumber() { return phoneNumber; }
    public double getTaxPercentage() { return taxPercentage; }
    public double getMinPrice() { return minPrice; }
    public int getMaxOrders() { return maxOrders; }
    public String getReceiptMessage() { return receiptMessage; }
    public double getMinSalary() { return minSalary; }
    public String getDefaultPaymentMethod() { return defaultPaymentMethod; }

    public void setName(String name) {
        this.name = (ValidationUtils.isValidText(name)) ? name : "Unnamed Restaurant";
    }

    public void setAddress(String address) {
        this.address = (ValidationUtils.isValidText(address)) ? address : "No address";
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (ValidationUtils.isValidPhoneNumber(phoneNumber)) ? phoneNumber : "No phone number";
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = (taxPercentage >= 0) ? taxPercentage : 0;
    }

    public void setMinPrice(double price) {
        this.minPrice = (price >= 0) ? price : 0;
    }

    public void setMaxOrders(int orders) {
        this.maxOrders = (orders >= 1) ? orders : 1;
    }

    public void setReceiptMessage(String message) {
        this.receiptMessage = (ValidationUtils.isValidText(message)) ? message : "";
    }

    public void setMinSalary(double salary) {
        this.minSalary = (salary >= 0) ? salary : 0;
    }

    public void setDefaultPaymentMethod(String paymentMethod) {
        this.defaultPaymentMethod = (ValidationUtils.isValidText(paymentMethod)) ? paymentMethod : "Cash";
    }
}
