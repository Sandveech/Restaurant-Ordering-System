package src.main.java.com.restaurant;

import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Table;
import src.main.java.com.restaurant.model.Admin;
import src.main.java.com.restaurant.model.Category;

public class RestaurantSystem {
    private HashMap<String, Category> categories = new HashMap<>();
    private ArrayList<MenuItem> items = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();

    private Admin admin = new Admin("admin", "admin", "", "", "", 0);
}
