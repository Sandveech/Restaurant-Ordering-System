package src.main.java.com.restaurant;

import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.Manager;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Table;
import src.main.java.com.restaurant.model.Waiter;
import src.main.java.com.restaurant.model.Admin;
import src.main.java.com.restaurant.model.Cashier;
import src.main.java.com.restaurant.model.Category;

public class RestaurantSystem {
    public HashMap<String, Category> categories = new HashMap<>();
    public ArrayList<MenuItem> items = new ArrayList<>();
    public ArrayList<Table> tables = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();
    public Employee user;

    public RestaurantSystem() {
        // categories
        categories.put("appetizers", new Category("Appetizers", "A small serving of food meant to be eaten before an entree, and often shared by several people."));
        categories.put("entree", new Category("Entree", "A dish served before the main course of a meal."));
        categories.put("main_course", new Category("Main Course", "The featured or primary dish in a meal consisting of several courses."));
        categories.put("dessert", new Category("Dessert", "A dish that consists of sweet foods, such as cake, biscuits, and ice cream."));

        // menu items
        items.add(new MenuItem("Aglio e Olio", "A classic Italian pasta dish from Naples, translating directly to \"garlic and oil\"", categories.get("main_course"), true));
        MenuItem aglio_e_olio = items.get(0);
        aglio_e_olio .createPriceOption("S", 5);
        aglio_e_olio .createPriceOption("M", 7.5);
        aglio_e_olio .createPriceOption("L", 10);

        items.add(new MenuItem("Carbonara", "A classic Roman pasta dish made by tossing hot pasta with a creamy sauce of raw eggs, grated Pecorino Romano cheese, and cured pork, and black pepper.", categories.get("main_course"), true));
        MenuItem carbonara = items.get(1);
        carbonara.createPriceOption("S", 6);
        carbonara.createPriceOption("M", 9);
        carbonara.createPriceOption("L", 12);

        // tables
        tables.add(new Table(4, true));
        tables.add(new Table(2, true));
        tables.add(new Table(6, true));
        tables.add(new Table(5, true));

        // employees
        employees.add(new Admin("admin", "admin", "", "", "", 0, "admin", "12345678"));
        employees.add(new Waiter("Hout", "Khongdara", "Male", "houtkhongdara@gmail.com", "123456789", 300, "hdara", "12345678"));
        employees.add(new Cashier("Phok", "Phallaoudom", "Male", "phokphallaoudom@gmail.com", "987654321", 300, "poudom", "12345678"));
        employees.add(new Manager("Cheviseth", "Waddhanayu", "Male", "chevisethwaddhanayu@gmail.com", "800813512", 300, "cwaddhanayu", "12345678"));
    }

    public void login(String username, String password) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                user = employee;
                System.out.println("Success: Logged in as \'" + employee.getFullName() + "\'");
                return;
            }
        }
        System.out.println("Failed: invalid username or passsword");
    }

    public boolean addCashier(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password) {
        Cashier temp = new Cashier(first_name, last_name, gender, email, phone_number, salary, username, password);
        return true;
    }
}
