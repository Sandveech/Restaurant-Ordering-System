package src.main.java.com.restaurant.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Receipt {
    private static int count = 0;
    private int id;
    private TableOrder table_order;
    private Employee cashier;
    private LocalDateTime issued_at;
    private double tax_percentage;
    private double total_price;

    // constructor
    public Receipt(TableOrder table_order, Employee cashier, double tax_percentage) {
        setID(count++);
        setTableOrder(table_order);
        setCashier(cashier);
        setIssuedAt(LocalDateTime.now());
        setTaxPercentage(tax_percentage);
        setTotalPrice(calculateTotalPrice());
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Table: #%d, Cashier: %s, Issued At: %s, Tax Percentage: %.2f, Total Price: %.2f", id, getCashier().getFullName(), getFormattedDateTime(), tax_percentage, total_price);
    }

    // getters and setters
    private int getID() { return id; }
    private TableOrder getTableOrder() { return table_order; }
    private Employee getCashier() { return cashier; }
    private LocalDateTime getIssuedAt() { return issued_at; }
    public double getTaxPercentage() { return tax_percentage; }
    public double getTotalPrice() { return total_price; }
    public String getFormattedDate() { return issued_at.format(DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT)); }
    public String getFormattedTime() { return issued_at.format(DateTimeFormatter.ofPattern(AppConstants.TIME_FORMAT)); }
    public String getFormattedDateTime() { return issued_at.format(DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT + " " + AppConstants.TIME_FORMAT)); }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    private void setTableOrder(TableOrder table_order) {
        if (table_order != null) { this.table_order = table_order; }
    }

    private void setCashier(Employee cashier) {
        if (cashier != null) { this.cashier = cashier; }
    }

    private void setIssuedAt(LocalDateTime date) {
        if (date != null) { this.issued_at = date; }
    }

    private void setTaxPercentage(double percentage) {
        this.tax_percentage = (percentage >= 0) ? percentage : 0;
    }

    private void setTotalPrice(double price) {
        this.total_price = (price >= 0) ? price : 0;
    }

    // helper functions
    private double calculateTotalPrice() {
        if (table_order == null) { return 0; }

        double total = 0;

        for (OrderItem o : table_order.getOrders()) {
            if (o == null) { continue; }
            total += o.getSubtotal();
        }

        return total;
    }

    public double calculateTotalPriceWithTax() {
        return total_price + (total_price * (tax_percentage / 100));
    }

    public void displayInfo() {
        RestaurantConfig restaurant = RestaurantConfig.getInstance();
        System.out.println("===" + restaurant.getName() + "===");
        System.out.println("Address: " + restaurant.getAddress());
        System.out.println("Phone Number: " + restaurant.getPhoneNumber());
        System.out.println("Date: " + getFormattedDateTime());
        System.out.println("Cashier: " + getCashier().getFullName());
        System.out.println("Table: #" + getTableOrder().getTable().getNumber());
        System.out.println("===ITEMS===");
        getTableOrder().displayOrders();
        System.out.println("===TOTAL===");
        System.out.println(String.format("$%.2f -> $%.2f (%.2f%% VAT)", total_price, calculateTotalPriceWithTax(), getTaxPercentage()));
    }
}
