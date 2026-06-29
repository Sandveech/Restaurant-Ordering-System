package src.main.java.com.restaurant.services;

import src.main.java.com.restaurant.model.Table;
import src.main.java.com.restaurant.repository.RestaurantDataStore;

public class TableService {
    private final RestaurantDataStore data;

    public TableService(RestaurantDataStore data) {
        this.data = data;
    }

    public void addTable(Table table) {
        if (!isValidTable(table)) { return; }
        
        data.getTables().add(table);
    }

    public void addTable(int seatCount, boolean active) {
        if (!isValidSeatCount(seatCount)) { return; }

        Table temp = new Table(seatCount, active);
        data.getTables().add(temp);
    }

    public void addTable(int seatCount) {
        if (!isValidSeatCount(seatCount)) { return; }

        Table temp = new Table(seatCount);
        data.getTables().add(temp);
    }

    private boolean isValidTable(Table table) {
        if (table == null) {
            System.out.println("null table.");
            return false;
        }

        return true;
    }

    private boolean isValidSeatCount(int count) {
        if (count <= 0) {
            System.out.println("Seat count cannot be zero or negative.");
            return false;
        }

        return true;
    }
}
