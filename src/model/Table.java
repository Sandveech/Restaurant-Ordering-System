package src.model;

import src.config.AppConstants;
import src.util.ValidationUtils;

public class Table {
    private static int count = 0;
    private int id;
    private int seat_count;
    private Boolean reserved = false;

    public Table(int seat_count) {
        setID(count++);
        setSeatCount(seat_count);
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
        this.seat_count = (seat_count >= 1) ? n : 1;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    // logic
    public void clearTable() {
        this.reserved = false;
    }
}
