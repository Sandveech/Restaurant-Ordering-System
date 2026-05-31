package src.main.java.com.restaurant.repository;

import java.util.ArrayList;

import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Receipt;
import src.main.java.com.restaurant.model.Table;
import src.main.java.com.restaurant.model.TableOrder;

public class RestaurantDataStore {
    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<MenuItem> menu_items = new ArrayList<>();
    private static ArrayList<Table> tables = new ArrayList<>();
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<TableOrder> table_orders = new ArrayList<>();
    private static ArrayList<Receipt> receipts = new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menu_items;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<TableOrder> getTableOrders() {
        return table_orders;
    }

    public ArrayList<Receipt> getReceipts() {
        return receipts;
    }
}
