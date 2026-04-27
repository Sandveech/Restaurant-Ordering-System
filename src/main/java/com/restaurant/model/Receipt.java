package src.main.java.com.restaurant.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Receipt {
    private static int count = 0;
    private int id;
    private Cart cart;
    private Table table;
    private Employee cashier;
    private LocalDateTime issued_at;
    private double tax_percentage;
    private double total_price;

    // constructor
    public Receipt(Cart cart, Table table, Employee cashier, double tax_percentage) {
        setID(count++);
        setCart(cart);
        setTable(table);
        setCashier(cashier);
        setIssuedAt(LocalDateTime.now());
        setTaxPercentage(tax_percentage);
        setTotalPrice(calculateTotalPrice());
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Cart: %p, Table: #%d, Cashier: %s, Issued At: %s, Tax Percentage: %.2f, Total Price: %.2f", id, getCart(), getTable().getNumber(), getCashier().getFullName(), getFormattedDateTime(), tax_percentage, total_price);
    }

    // getters and setters
    private int getID() { return id; }
    private Cart getCart() { return cart; }
    private Table getTable() { return table; }
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

    private void setCart(Cart cart) {
        if (cart != null) { this.cart = cart; }
    }

    private void setTable(Table table) {
        if (table != null) { this.table = table; }
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
        double total = 0;

        if (cart != null) {
            for (Order order : getCart().getOrders()) {
                if (order == null) { continue; }
                total += order.calculateTotalPrice();
            }
        }
        
        return total;
    }

    public double calculateTotalPriceWithTax() {
        return total_price + (total_price * (tax_percentage / 100));
    }
}
