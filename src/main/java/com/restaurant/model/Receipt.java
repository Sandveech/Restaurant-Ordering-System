package src.main.java.com.restaurant.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Receipt implements Displayable {
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

        if (table_order != null) { setTotalPrice(table_order.calculateTotalPrice()); }
        else { setTotalPrice(0); }
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Table: #%d, Cashier: %s, Issued At: %s, Tax Percentage: %.2f, Total Price: %.2f", id, getCashier().getFullName(), getFormattedDateTime(), tax_percentage, total_price);
    }

    // getters and setters
    /**
     * Returns the id of this receipt.
     * @return the id of this receipt
     */
    private int getID() { return id; }

    /**
     * Returns the table order of this receipt.
     * @return the table order of this receipt
     */
    private TableOrder getTableOrder() { return table_order; }

    /**
     * Returns the cashier of this receipt.
     * @return the cashier of this receipt
     */
    private Employee getCashier() { return cashier; }

    /**
     * Returns the date this receipt was issued at.
     * @return the date this receipt was issued at
     */
    private LocalDateTime getIssuedAt() { return issued_at; }

    /**
     * Returns the tax percentage of this receipt.
     * @return the tax percentage of this receipt
     */
    public double getTaxPercentage() { return tax_percentage; }

    /**
     * Returns the total price of this receipt.
     * @return the total price of this receipt
     */
    public double getTotalPrice() { return total_price; }

    /**
     * Returns the issued date of this receipt as a formatted string.
     * @return the issued date of this receipt as a formatted string
     */
    public String getFormattedDate() { return issued_at.format(DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT)); }

    /**
     * Returns the issued time of this receipt as a formatted string.
     * @return the issued time of this receipt as a formatted string
     */
    public String getFormattedTime() { return issued_at.format(DateTimeFormatter.ofPattern(AppConstants.TIME_FORMAT)); }

    /**
     * Returns the issued date and time of this receipt as a formatted string.
     * @return the issued date and time of this receipt as a formatted string
     */
    public String getFormattedDateTime() { return issued_at.format(DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT + " " + AppConstants.TIME_FORMAT)); }

    /**
     * Sets the id of this receipt
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the table order of this receipt.
     * @param table_order the table order to set to
     */
    private void setTableOrder(TableOrder table_order) {
        if (table_order != null) { this.table_order = table_order; }
    }

    /**
     * Sets the cashier of this receipt.
     * @param cashier the cashier of this receipt
     */
    private void setCashier(Employee cashier) {
        if (cashier != null) { this.cashier = cashier; }
    }

    /**
     * Sets the issued date and time of this receipt.
     * @param date the issued date and time of this receipt
     */
    private void setIssuedAt(LocalDateTime date) {
        if (date != null) { this.issued_at = date; }
    }

    /**
     * Sets the tax percentage of this receipt.
     * @param percentage the tax percentage to set to
     */
    private void setTaxPercentage(double percentage) {
        this.tax_percentage = (percentage >= 0) ? percentage : 0;
    }

    /**
     * Sets the total price of this receipt.
     * @param price the total price to set to
     */
    private void setTotalPrice(double price) {
        this.total_price = (price >= 0) ? price : 0;
    }

    // helper functions
    /**
     * Calculates and returns the total price of this receipt with tax.
     * @return the total price of this receipt with tax
     */
    public double calculateTotalPriceWithTax() {
        return total_price + (total_price * (tax_percentage / 100));
    }

    public void displaySeperator(String symbol, int width) {
        int i = 0;
        while (i++ < width) { System.out.print(symbol); }
        System.out.println();
    }

    public void display() {
        RestaurantConfig restaurant = RestaurantConfig.getInstance();
        System.out.println(restaurant.getName());
        System.out.println(restaurant.getAddress());
        System.out.println("Phone: " + restaurant.getPhoneNumber());
        System.out.println(getFormattedDateTime());
        String cashier_name = (cashier != null) ? cashier.getFullName() : "Unknown cashier";
        System.out.println("Cashier: " + cashier_name);

        int table_number = (table_order != null && table_order.getTable() != null) ? table_order.getTable().getNumber() : 0;
        System.out.println("Table: #" + table_number);

        displaySeperator("-", 48);

        System.out.println(String.format("%-32s %-8s", "DESC.", "QTY."));
        getTableOrder().display();
        
        System.out.println(String.format("\nSubtotal %39.2f", total_price));
        System.out.println(String.format("VAT %42.2f %%", getTaxPercentage()));

        displaySeperator("-", 48);

        System.out.println(String.format("Total %42.2f", calculateTotalPriceWithTax()));

        System.out.println("\n" + RestaurantConfig.getInstance().getReceiptMessage());
    }
}
