package src.main.java.com.restaurant;

import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.model.TableOrder;
import src.main.java.com.restaurant.model.Waiter;
import src.main.resources.SampleData;
import src.main.java.com.restaurant.model.Cashier;
import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Receipt;
import src.main.java.com.restaurant.model.Table;

public class Main {
    public static void main(String[] args) {
        // DISCLAIMER: THIS IS FOR THE PURPOSE OF TESTING

        // sample data
        HashMap<String, Category> categories = SampleData.getCategories();
        ArrayList<MenuItem> menu = SampleData.getMenuItems();
        ArrayList<Table> tables = SampleData.getTables();
        ArrayList<Employee> employees = SampleData.getEmployees();

        Waiter waiter = (Waiter) employees.getFirst();
        TableOrder table_order = waiter.createTableOrder(tables.getFirst());
        table_order.addOrder(menu.get(0), 1, "S");
        table_order.addOrder(menu.get(1), 1, "S");
        table_order.addOrder(menu.get(0), 3, "S");
        table_order.addOrder(menu.get(0), 1, "M");
        table_order.addOrder(menu.get(1), 3, "L");
        table_order.complete();

        Cashier cashier = (Cashier) employees.get(1);
        Receipt receipt = cashier.generateReceipt(table_order, RestaurantConfig.getInstance().getTaxPercentage(), "Cash");

        if (receipt != null) { receipt.print(); }
    }
}
