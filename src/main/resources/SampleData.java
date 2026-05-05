package src.main.resources;

import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.JobRole;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Table;

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
        items.get(0).createPriceOption("S", 5);
        items.get(0).createPriceOption("M", 7.5);
        items.get(0).createPriceOption("L", 10);

        // carbonara
        items.add(new MenuItem("Carbonara", "A classic Roman pasta dish made by tossing hot pasta with a creamy sauce of raw eggs, grated Pecorino Romano cheese, and cured pork, and black pepper.", categories.get("main_course"), true));
        items.get(1).createPriceOption("S", 6);
        items.get(1).createPriceOption("M", 9);
        items.get(1).createPriceOption("L", 12);

        return items;
    }

    public static ArrayList<Table> getTables() {
        ArrayList<Table> tables = new ArrayList<Table>();

        tables.add(new Table(4));
        tables.add(new Table(2));
        tables.add(new Table(6));
        tables.add(new Table(5));

        return tables;
    }

    public static HashMap<String, JobRole> getJobRoles() {
        HashMap<String, JobRole> roles = new HashMap<String, JobRole>();

        roles.put("waiter", new JobRole("Waiter"));
        roles.put("cashier", new JobRole("Cashier"));
        roles.put("chef", new JobRole("Chef"));

        return roles;
    }

    public static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        HashMap<String, JobRole> roles = getJobRoles();

        employees.add(new Employee("Hout", "Khongdara", "Male", "houtkhongdara@gmail.com", "123456789", roles.get("waiter")));
        employees.add(new Employee("Phok", "Phallaoudom", "Male", "phokphallaoudom@gmail.com", "987654321", roles.get("cashier")));
        employees.add(new Employee("Cheviseth", "Waddhanayu", "Male", "chevisethwaddhanayu@gmail.com", "800813512", roles.get("chef")));

        return employees;
    }
}
