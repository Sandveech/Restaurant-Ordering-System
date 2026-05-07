package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Table implements Displayable, Reservable {
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
    /**
     * Returns the historical count of tables.
     * @return the historical count of tables
     */
    private int getCount() { return count; }
    
    /**
     * Returns the id of this table.
     * @return the id of this table
     */
    private int getID() { return id; }

    /**
     * Returns the seat count of this table.
     * @return the seat count of this table
     */
    public int getSeatCount() { return seat_count; }

    /**
     * Returns the number of this table.
     * @return the number of this table
     */
    public int getNumber() { return id + 1; } 

    /**
     * Returns {@true} if this table is reserved; otherwise, {@false}.
     * @return {@true} if this table is reserved; otherwise, {@false}
     */
    public Boolean isReserved() { return reserved; }

    /**
     * Sets the id of this table.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the seat count of this table.
     * @param n the seat count to set to
     */
    private void setSeatCount(int n) {
        this.seat_count = (n >= 1) ? n : 1;
    }

    /**
     * Sets the reservation state of this table.
     * @param reserved {@true} if this table is to be reserved; otherwise, {@false}
     */
    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    // logic
    /**
     * Reserves this table.
     */
    public void reserve() {
        this.reserved = true;
    }

    /**
     * Cancels the reservation of this table
     */
    public void cancelReservation() {
        this.reserved = false;
    }

    /**
     * Displays this table.
     */
    public void display() {
        System.out.println(String.format("Table: #%d, Seat Count: %d, Reserved?: %b", getNumber(), seat_count, reserved));
    }
}
