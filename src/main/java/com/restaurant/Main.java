package src.main.java.com.restaurant;

import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.model.Cart;
import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.JobRole;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Receipt;
import src.main.java.com.restaurant.model.Table;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Category> categories = new HashMap<String, Category>();
        categories.put("appetizers", new Category("Appetizers", "A small serving of food meant to be eaten before an entree, and often shared by several people."));
        categories.put("entree", new Category("Entree", "A dish served before the main course of a meal."));
        categories.put("main_course", new Category("Main Course", "The featured or primary dish in a meal consisting of several courses."));
        categories.put("dessert", new Category("Dessert", "A dish that consists of sweet foods, such as cake, biscuits, and ice cream."));

        HashMap<String, MenuItem> menu_items = new HashMap<String, MenuItem>();
        menu_items.put("aglio_e_olio", new MenuItem("Aglio e Olio", "A classic Italian pasta dish from Naples, translating directly to \"garlic and oil\"", categories.get("main_course"), 5, true));
        menu_items.put("carbonara", new MenuItem("Carbonara", "A classic Roman pasta dish made by tossing hot pasta with a creamy sauce of raw eggs, grated Pecorino Romano cheese, and cured pork, and black pepper.", categories.get("main_course"), 6, true));

        ArrayList<Table> tables = new ArrayList<Table>();
        tables.add(new Table(4));

        HashMap<String, JobRole> job_roles = new HashMap<String, JobRole>();
        job_roles.put("waiter", new JobRole("Waiter"));
        job_roles.put("cashier", new JobRole("Cashier"));

        ArrayList<Employee> waiters = new ArrayList<Employee>();
        waiters.add(new Employee("Hout", "Khongdara", "Male", "houtkhongdara@gmail.com", "123456789", job_roles.get("waiter")));

        ArrayList<Employee> cashiers = new ArrayList<Employee>();
        cashiers.add(new Employee("Phok", "Phallaoudom", "Male", "phokphallaoudom@gmail.com", "987654321", job_roles.get("cashier")));

        Cart cart = new Cart(waiters.getFirst(), tables.getFirst());
        cart.addOrder(menu_items.get("aglio_e_olio"), 1, "small");
        cart.addOrder(menu_items.get("carbonara"), 1, "small");
        cart.addOrder(menu_items.get("aglio_e_olio"), 3, "small");
        cart.addOrder(menu_items.get("aglio_e_olio"), 1, "medium");

        Receipt receipt = new Receipt(cart, tables.getFirst(), cashiers.getFirst(), RestaurantConfig.getInstance().getTaxPercentage());

        System.out.println("===CATEGORIES===");
        categories.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("===MENU ITEMS===");
        menu_items.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("===TABLES===");
        for (Table table : tables) { System.out.println(table); }

        System.out.println("===JOB ROLES===");
        job_roles.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("===WAITERS===");
        for (Employee waiter : waiters) { System.out.println(waiter); }

        System.out.println("===CASHIERS===");
        for (Employee cashier : cashiers) { System.out.println(cashier); }
    }
}
