package src.main.java.com.restaurant;

import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.model.TableOrder;
import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.JobRole;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Receipt;
import src.main.java.com.restaurant.model.Table;

public class Main {
    public static void main(String[] args) {
        // categories
        HashMap<String, Category> categories = new HashMap<String, Category>();
        categories.put("appetizers", new Category("Appetizers", "A small serving of food meant to be eaten before an entree, and often shared by several people."));
        categories.put("entree", new Category("Entree", "A dish served before the main course of a meal."));
        categories.put("main_course", new Category("Main Course", "The featured or primary dish in a meal consisting of several courses."));
        categories.put("dessert", new Category("Dessert", "A dish that consists of sweet foods, such as cake, biscuits, and ice cream."));

        // menu items
        ArrayList<MenuItem> menu_items = new ArrayList<MenuItem>();
        menu_items.add(new MenuItem("Aglio e Olio", "A classic Italian pasta dish from Naples, translating directly to \"garlic and oil\"", categories.get("main_course"), 5, true));
        menu_items.add(new MenuItem("Carbonara", "A classic Roman pasta dish made by tossing hot pasta with a creamy sauce of raw eggs, grated Pecorino Romano cheese, and cured pork, and black pepper.", categories.get("main_course"), 6, true));

        // tables
        ArrayList<Table> tables = new ArrayList<Table>();
        tables.add(new Table(4));

        // job roles
        HashMap<String, JobRole> job_roles = new HashMap<String, JobRole>();
        job_roles.put("waiter", new JobRole("Waiter"));
        job_roles.put("cashier", new JobRole("Cashier"));

        // employees
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Hout", "Khongdara", "Male", "houtkhongdara@gmail.com", "123456789", job_roles.get("waiter")));
        employees.add(new Employee("Phok", "Phallaoudom", "Male", "phokphallaoudom@gmail.com", "987654321", job_roles.get("cashier")));

        TableOrder cart = new TableOrder(employees.getFirst(), tables.getFirst());
        cart.addOrder(menu_items.get(0), 1, "small");
        cart.addOrder(menu_items.get(1), 1, "small");
        cart.addOrder(menu_items.get(0), 3, "small");
        cart.addOrder(menu_items.get(0), 1, "medium");

        Receipt receipt = new Receipt(cart, tables.getFirst(), employees.getLast(), RestaurantConfig.getInstance().getTaxPercentage());

        receipt.displayInfo();
    }
}
