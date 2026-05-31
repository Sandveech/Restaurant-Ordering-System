package src.main.java.com.restaurant.services;

import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.model.Cashier;
import src.main.java.com.restaurant.model.Receipt;
import src.main.java.com.restaurant.model.TableOrder;
import src.main.java.com.restaurant.repository.RestaurantDataStore;
import src.main.java.com.restaurant.util.ValidationUtils;

public class ReceiptService {
    private final RestaurantDataStore data;

    public ReceiptService(RestaurantDataStore data) {
        this.data = data;
    }

    public Receipt generateReceipt(TableOrder table_order, Cashier cashier, double tax_percentage, String payment_method) {
        if (!isValidTableOrder(table_order)) { return null; }
        if (!isValidCashier((cashier))) { return null; }

        return new Receipt(table_order, cashier, tax_percentage, payment_method);
    }

    public Receipt generateReceipt(int table_order_id, Cashier cashier, double tax_percentage, String payment_method) {
        if (!isValidTableOrderID(table_order_id)) { return null; }
        if (!isValidCashier((cashier))) { return null; }

        TableOrder table_order = searchTableOrderByID(table_order_id);
        if (!isValidTableOrder(table_order)) { return null; }

        return new Receipt(table_order, cashier, tax_percentage, payment_method);
    }

    public TableOrder searchTableOrderByID(int id) {
        if (!isValidTableOrderID(id)) { return null; }

        for (TableOrder table_order : data.getTableOrders()) {
            if (table_order.getID() == id) {
                return table_order;
            }
        }

        return null;
    }

    private boolean isValidTableOrder(TableOrder table_order) {
        if (table_order == null) {
            System.out.println("Cashier cannot generate a receipt without a table order.");
            return false;
        }
        if (!table_order.isCompleted()) {
            System.out.println("Receipt can only be generated after the table order is completed.");
            return false;
        }

        return true;
    }

    private boolean isValidCashier(Cashier cashier) {
        if (cashier == null) {
            System.out.println("Cashier cannot be null.");
            return false;
        }

        return true;
    } 

    private boolean isValidTableOrderID(int id) {
        if (!ValidationUtils.isValidID(id) || id >= data.getTableOrders().size()) {
            System.out.println("id out of range.");
            return false;
        }

        return true;
    }
}
