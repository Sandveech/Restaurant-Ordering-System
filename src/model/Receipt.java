package src.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.config.AppConstants;
import src.util.ValidationUtils;

public class Receipt {
    private static int count = 0;
    private int id;
    private Cart cart;
    private Table table;
    private Employee cashier;
    private LocalDateTime issued_at;

    // constructor
    public Receipt(Cart cart, Table table, Employee cashier) {
        setID(count++);
        setCart(cart);
        setTable(table);
        setCashier(cashier);
        setIssuedAt(LocalDateTime.now());
    }

    // getters and setters
    private int getID() { return id; }
    private Cart getCart() { return cart; }
    private Table getTable() { return table; }
    private Employee getCashier() { return cashier; }
    private LocalDateTime getIssuedAt() { return issued_at; }
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
}
