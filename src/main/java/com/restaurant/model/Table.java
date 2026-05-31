package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.interfaces.Activatable;
import src.main.java.com.restaurant.interfaces.Displayable;
import src.main.java.com.restaurant.interfaces.Occupiable;
import src.main.java.com.restaurant.interfaces.Reservable;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Table implements Displayable, Reservable, Occupiable, Activatable {
    private static int table_count = 0;
    private int id;
    private int seat_count;
    private boolean reserved = false;
    private boolean occupied = false;
    private boolean active;

    public Table(int seat_count, Boolean active) {
        setID(table_count++);
        setSeatCount(seat_count);
        setActive(active);
    }

    public Table(int seat_count) {
        setID(table_count++);
        setSeatCount(seat_count);
        setActive(true);
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
    private static int getCount() { return table_count; }
    
    /**
     * Returns the id of this table.
     * @return the id of this table
     */
    public int getID() { return id; }

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
     * Returns a value indicating whether this table is reserved.
     * @return {@true} if this table is reserved; otherwise, {@false}
     */
    @Override
    public boolean isReserved() { return reserved; }

    /**
     * Returns a value indicating whether this table is active.
     * @return {@true} if this table is active; otherwise, {@false}
     */
    @Override
    public boolean isActive() { return active; }
    
    /**
     * Returns a value indicating whether this table is occupied.
     * @return {@true} if this table is occupied; otherwise, {@false}
     */
    @Override
    public boolean isOccupied() { return occupied; }

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

    /**
     * Sets the occupation state of this table.
     * @param occupied {@true} if this table is to be occupied; otherwise, {@false}
     */
    private void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Sets the active state of this table.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Reserves this table.
     */
    @Override
    public void reserve() {
        this.reserved = true;
    }

    /**
     * Cancels the reservation of this table
     */
    @Override
    public void cancelReservation() {
        this.reserved = false;
    }

    /**
     * Occupies this table.
     */
    @Override
    public boolean occupy() {
        if (isActive()) { this.occupied = true; }
        return this.occupied;
    }

    /**
     * Unoccupies this table.
     */
    @Override
    public void unoccupy() {
        this.occupied = false;
    }

    /**
     * Displays this table.
     */
    @Override
    public void display() {
        System.out.println("- Table #" + getNumber());
        System.out.println("    + Seat Count: " + seat_count);
        System.out.println("    + Active?: " + active);
        System.out.println("    + Occupied?: " + occupied);
        System.out.println("    + Reserved?: " + reserved);
    }

    /**
     * Activates this table.
     */
    public void activate() {
        this.active = true;
    }

    /**
     * Deactivates this table.
     */
    public void deactivate() {
        this.active = false;
    }
}
