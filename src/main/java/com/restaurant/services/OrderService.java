package src.main.java.com.restaurant.services;

import src.main.java.com.restaurant.model.Table;
import src.main.java.com.restaurant.model.TableOrder;
import src.main.java.com.restaurant.model.Waiter;
import src.main.java.com.restaurant.repository.RestaurantDataStore;
import src.main.java.com.restaurant.util.ValidationUtils;

public class OrderService {
    private final RestaurantDataStore data;

    public OrderService(RestaurantDataStore data) {
        this.data = data;
    }

    public void addTableOrder(TableOrder table_order) {
        if (!isValidTableOrder(table_order)) { return; }

        data.getTableOrders().add(table_order);
    }

    /**
     * Creates a new table order for the specified table.
     * @param table the table to take the order of
     * @return the table order of the specified table
     */
    public void createTableOrder(Table table, Waiter waiter) {
        if (!isValidTable(table)) { return; }

        table.occupy();

        if (!table.isOccupied()) {
            System.out.println("Table " + table.getNumber() + " cannot be occupied.");
            return;
        }

        TableOrder table_order = new TableOrder(waiter, table);
        data.getTableOrders().add(table_order);
    }

    private boolean isValidTable(Table table) {
        if (table == null) {
            System.out.println("Watier cannot create an order without a table.");
            return false;
        }
        if (table.isOccupied()) {
            System.out.println("Table " + table.getNumber() + "is already occupied.");
            return false;
        }

        return true;
    }

    private Table searchTableByNumber(int n) {
        for (Table table : data.getTables()) {
            if (table.getNumber() == n) { return table; }
        }

        return null;
    }

    private Table searchTableByID(int id) {
        if (!isValidTableID(id)) { return null; }

        for (Table table : data.getTables()) {
            if (table.getID() == id) { return table; }
        }

        return null;
    }

    private boolean isValidTableID(int id) {
        if (!ValidationUtils.isValidID(id) || id >= data.getTables().size()) {
            System.out.println("Table id out of range.");
            return false;
        }

        return true;
    } 

    private boolean isValidTableOrder(TableOrder table_order) {
        if (table_order == null) {
            System.out.println("null table order.");
            return false;
        }

        return true;
    }
}   
