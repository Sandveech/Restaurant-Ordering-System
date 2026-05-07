package src.main.java.com.restaurant;

import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.model.TableOrder;
import src.main.resources.SampleData;
import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.JobRole;
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
        HashMap<String, JobRole> roles = SampleData.getJobRoles();
        ArrayList<Employee> employees = SampleData.getEmployees();

        TableOrder table_order = new TableOrder(employees.getFirst(), tables.getFirst());
        table_order.addOrder(menu.get(0), 1, "S");
        table_order.addOrder(menu.get(1), 1, "S");
        table_order.addOrder(menu.get(0), 3, "S");
        table_order.addOrder(menu.get(0), 1, "M");
        table_order.addOrder(menu.get(1), 3, "L");

        Receipt receipt = new Receipt(table_order, employees.get(0), RestaurantConfig.getInstance().getTaxPercentage());

        receipt.print();
    }
}
