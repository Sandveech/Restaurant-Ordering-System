package src.main.resources;

import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.com.restaurant.model.Cashier;
import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.JobRole;
import src.main.java.com.restaurant.model.Manager;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Table;
import src.main.java.com.restaurant.model.Waiter;

public class SampleData {
    public static HashMap<String, Category> getCategories() {
        HashMap<String, Category> categories = new HashMap<String, Category>();

        categories.put("appetizers", new Category("Appetizers", "A small serving of food meant to be eaten before an entree, and often shared by several people."));
        categories.put("entree", new Category("Entree", "A dish served before the main course of a meal."));
        categories.put("main_course", new Category("Main Course", "The featured or primary dish in a meal consisting of several courses."));
        categories.put("dessert", new Category("Dessert", "A dish that consists of sweet foods, such as cake, biscuits, and ice cream."));

        return categories;
    }

    public static ArrayList<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        HashMap<String, Category> categories = getCategories();

        // algio e olio
        items.add(new MenuItem("Aglio e Olio", "A classic Italian pasta dish from Naples, translating directly to \"garlic and oil\"", categories.get("main_course"), true));
        MenuItem aglio_e_olio = items.get(0);
        aglio_e_olio .createPriceOption("S", 5);
        aglio_e_olio .createPriceOption("M", 7.5);
        aglio_e_olio .createPriceOption("L", 10);

        // carbonara
        items.add(new MenuItem("Carbonara", "A classic Roman pasta dish made by tossing hot pasta with a creamy sauce of raw eggs, grated Pecorino Romano cheese, and cured pork, and black pepper.", categories.get("main_course"), true));
        MenuItem carbonara = items.get(1);
        carbonara.createPriceOption("S", 6);
        carbonara.createPriceOption("M", 9);
        carbonara.createPriceOption("L", 12);

        return items;
    }

    public static ArrayList<Table> getTables() {
        ArrayList<Table> tables = new ArrayList<Table>();

        tables.add(new Table(4, true));
        tables.add(new Table(2, true));
        tables.add(new Table(6, true));
        tables.add(new Table(5, true));

        return tables;
    }

    public static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();

        employees.add(new Waiter("Hout", "Khongdara", "Male", "houtkhongdara@gmail.com", "123456789", 300));
        employees.add(new Cashier("Phok", "Phallaoudom", "Male", "phokphallaoudom@gmail.com", "987654321", 300));
        employees.add(new Manager("Cheviseth", "Waddhanayu", "Male", "chevisethwaddhanayu@gmail.com", "800813512", 300));

        return employees;
    }
}
