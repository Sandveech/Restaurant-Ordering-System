package src.main.java.com.restaurant.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.interfaces.Calculatable;
import src.main.java.com.restaurant.interfaces.Printable;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Receipt implements Printable, Calculatable {
    private static int receiptCount = 0;
    private int id;
    private TableOrder tableOrder;
    private Cashier cashier;
    private LocalDateTime issuedAt;
    private double subtotalPrice;
    private double taxPercentage;
    private double taxAmount;
    private String paymentMethod;

    // constructor
    public Receipt(TableOrder tableOrder, Cashier cashier, double taxPercentage, String paymentMethod) {
        setID(receiptCount++);
        setTableOrder(tableOrder);
        setCashier(cashier);
        setIssuedAt(LocalDateTime.now());
        setTaxPercentage(taxPercentage);
        setPaymentMethod(paymentMethod);

        setSubtotalPrice(calculateSubtotalPrice());
        setTaxAmount(calculateTaxAmount(taxPercentage));
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Table: #%d, Cashier: %s, Issued At: %s, Tax Percentage: %.2f, Total Price: %.2f", id, getCashier().getFullName(), getFormattedDateTime(), taxPercentage, calculate());
    }

    /**
     * Returns the historical count of receipts.
     * @return the historical count of receipts
     */
    private static int getReceiptCount() {
        return receiptCount;
    }

    /**
     * Returns the id of this receipt.
     * @return the id of this receipt
     */
    private int getID() { return id; }

    /**
     * Returns the table order of this receipt.
     * @return the table order of this receipt
     */
    private TableOrder getTableOrder() { return tableOrder; }

    /**
     * Returns the cashier of this receipt.
     * @return the cashier of this receipt
     */
    private Cashier getCashier() { return cashier; }

    /**
     * Returns the date this receipt was issued at.
     * @return the date this receipt was issued at
     */
    private LocalDateTime getIssuedAt() { return issuedAt; }

    /**
     * Returns the subtotal price of this receipt.
     * @return the subtotal price of this receipt
     */
    public double getSubtotalPrice() { return subtotalPrice; }

    /**
     * Returns the tax percentage of this receipt.
     * @return the tax percentage of this receipt
     */
    public double getTaxPercentage() { return taxPercentage; }

    /**
     * Returns the tax amount of this receipt.
     * @return the tax amount of this receipt
     */
    public double getTaxAmount() { return taxAmount; }

    /**
     * Returns the payment method of this receipt.
     * @return the payment method of this receipt
     */
    public String getPaymentMethod() { return paymentMethod; }

    /**
     * Returns the issued date of this receipt as a formatted string.
     * @return the issued date of this receipt as a formatted string
     */
    public String getFormattedDate() { return issuedAt.format(DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT)); }

    /**
     * Returns the issued time of this receipt as a formatted string.
     * @return the issued time of this receipt as a formatted string
     */
    public String getFormattedTime() { return issuedAt.format(DateTimeFormatter.ofPattern(AppConstants.TIME_FORMAT)); }

    /**
     * Returns the issued date and time of this receipt as a formatted string.
     * @return the issued date and time of this receipt as a formatted string
     */
    public String getFormattedDateTime() { return issuedAt.format(DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT + " " + AppConstants.TIME_FORMAT)); }

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
    private void setTableOrder(TableOrder tableOrder) {
        if (tableOrder != null) { this.tableOrder = tableOrder; }
    }

    /**
     * Sets the cashier of this receipt.
     * @param cashier the cashier of this receipt
     */
    private void setCashier(Cashier cashier) {
        if (cashier != null) { this.cashier = cashier; }
    }

    /**
     * Sets the issued date and time of this receipt.
     * @param date the issued date and time of this receipt
     */
    private void setIssuedAt(LocalDateTime date) {
        if (date != null) { this.issuedAt = date; }
    }

    /**
     * Sets the subtotal price of this receipt.
     * @param subtotal_price the subtotal price to set to
     */
    private void setSubtotalPrice(double subtotalPrice) {
        this.subtotalPrice = (subtotalPrice < 0) ? 0 : subtotalPrice;
    }

    /**
     * Sets the tax percentage of this receipt.
     * @param percentage the tax percentage to set to
     */
    private void setTaxPercentage(double percentage) {
        this.taxPercentage = (percentage < 0) ? 0 : percentage;
    }

    /**
     * Sets the tax amount of this receipt.
     * @param amount the tax amount to set to
     */
    private void setTaxAmount(double amount) {
        this.taxAmount = (amount < 0) ? 0 : amount;
    }

    /**
     * Sets the payment method of this receipt.
     * @param payment_method the payment method to set to
     */
    private void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // helper functions
    @Override
    public double calculate() {
        return subtotalPrice + taxAmount;
    }

    private double calculateSubtotalPrice() {
        return (tableOrder != null) ? tableOrder.calculate() : 0;
    }

    private double calculateTaxAmount(double taxPercentage) {
        return (calculateSubtotalPrice()) * (taxPercentage / 100);
    }

    public void displaySeperator(String symbol, int width) {
        int i = 0;
        while (i++ < width) { System.out.print(symbol); }
        System.out.println();
    }

    @Override
    public void print() {
        printHeader();
        printOrderDetails();
        printTotals();
        printFooter();
    }

    private void printHeader() {
        RestaurantConfig config = RestaurantConfig.getInstance();
        System.out.println(config.getName());
        System.out.println(config.getAddress());
        System.out.println("Phone: " + config.getPhoneNumber());
        System.out.println(getFormattedDateTime());

        String cashier_name = (cashier != null) ? cashier.getFullName() : "Unknown";
        int table_num = (tableOrder != null && tableOrder.getTable() != null) ? tableOrder.getTable().getNumber() : 0;

        System.out.printf("Cashier: %-29s Table: #%d%n", cashier_name, table_num);
        displaySeperator("-", 48);
    }

    private void printOrderDetails() {
        System.out.printf("%-32s %-8s%n", "DESC", "QTY");
        if (tableOrder != null) { tableOrder.display(); }
    }

    private void printTotals() {
        displaySeperator("-", 48);
        System.out.printf("Subtotal %39.2f%n", subtotalPrice);
        System.out.printf("%2.0f%% VAT %40.2f%n", taxPercentage, taxAmount);
        displaySeperator("-", 48);
    }

    private void printFooter() {
        System.out.printf("Total %42.2f%n", calculate());
        System.out.println("\n" + RestaurantConfig.getInstance().getReceiptMessage());
    }
    }
