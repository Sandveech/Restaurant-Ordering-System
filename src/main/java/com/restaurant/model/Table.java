package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Table {
    private static int count = 0;
    private int id;
    private int seat_count;
    private Boolean reserved = false;

    public Table(int seat_count) {
        setID(count++);
        setSeatCount(seat_count);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Number: %d, Seat Count: %d", id, getNumber(), seat_count);
    }

    // getters and setters
    private int getCount() { return count; }
    private int getID() { return id; }
    public int getSeatCount() { return seat_count; }
    public int getNumber() { return id + 1; } 
    public Boolean isReserved() { return reserved; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    private void setSeatCount(int n) {
        this.seat_count = (n >= 1) ? n : 1;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    // logic
    public void clearTable() {
        this.reserved = false;
    }
}
