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

    // constructor
    private RestaurantConfig() {
        this.name = "Unnamed Restaurant";
        this.address = "No address";
        this.phone_number = "No phone number";
        this.tax_percentage = 10;
        this.min_price = 0;
        this.max_orders = 32;
    };
    
    // getters and setters
    public static RestaurantConfig getInstance() { return INSTANCE; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phone_number; }
    public double getTaxPercentage() { return tax_percentage; }
    public double getMinPrice() { return min_price; }
    public int getMaxOrders() { return max_orders; }

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
}
