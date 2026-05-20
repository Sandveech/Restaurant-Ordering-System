package src.main.java.com.restaurant;

import java.util.ArrayList;

import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.ItemPriceOption;
import src.main.java.com.restaurant.model.Manager;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Receipt;
import src.main.java.com.restaurant.model.Table;
import src.main.java.com.restaurant.model.TableOrder;
import src.main.java.com.restaurant.model.Waiter;
import src.main.java.com.restaurant.util.ConsoleLogger;
import src.main.java.com.restaurant.enums.Action;
import src.main.java.com.restaurant.model.Admin;
import src.main.java.com.restaurant.model.Cashier;
import src.main.java.com.restaurant.model.Category;

public class RestaurantSystem {
    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<MenuItem> items = new ArrayList<>();
    private static ArrayList<Table> tables = new ArrayList<>();
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<TableOrder> table_orders = new ArrayList<>();
    private static ArrayList<Receipt> receipts = new ArrayList<>();
    private static Employee user;

    public RestaurantSystem() {
        // categories
        categories.add(new Category("Appetizers", "A small serving of food meant to be eaten before an entree, and often shared by several people."));
        categories.add(new Category("Entree", "A dish served before the main course of a meal."));
        categories.add(new Category("Main Course", "The featured or primary dish in a meal consisting of several courses."));
        categories.add(new Category("Dessert", "A dish that consists of sweet foods, such as cake, biscuits, and ice cream."));

        // menu items
        items.add(new MenuItem("Aglio e Olio", "A classic Italian pasta dish from Naples, translating directly to \"garlic and oil\"", categories.get(2), true));
        MenuItem aglio_e_olio = items.get(0);
        aglio_e_olio .createPriceOption("S", 5);
        aglio_e_olio .createPriceOption("M", 7.5);
        aglio_e_olio .createPriceOption("L", 10);

        items.add(new MenuItem("Carbonara", "A classic Roman pasta dish made by tossing hot pasta with a creamy sauce of raw eggs, grated Pecorino Romano cheese, and cured pork, and black pepper.", categories.get(2), true));
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
        Admin admin = new Admin("admin", "admin", "", "", "", 0, "admin", "12345678", null);
        employees.add(admin);
        employees.add(new Cashier("John", "Smith", "Male", "johnsmith@gmail.com", "123456789", 300, "johns", "12345678", admin));
        employees.add(new Waiter("John", "Doe", "Male", "johndoe@gmail.com", "987654321", 300, "johnd", "12345678", admin));
        employees.add(new Manager("Jane", "Doe", "Female", "janedoe@gmail.com", "800813512", 300, "janed", "12345678", admin));
    }

    public ArrayList<Category> getCategories() { return categories; }
    public ArrayList<MenuItem> getMenuItems() { return items; }
    public ArrayList<Table> getTables() { return tables; }
    public ArrayList<Employee> getEmployees() { return employees; }
    public ArrayList<TableOrder> getTableOrders() { return table_orders; }
    public ArrayList<Receipt> getReceipts() { return receipts; }

    public Employee getUser() { return user; }

    public void setUser(Employee user) {
        if (user != null) { this.user = user; }
    }

    public void login(String username, String password) {
        if (usernameExists(username)) {
            ConsoleLogger.printError("Username \'" + username + "\' already exists.");
            return;
        }

        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                user = employee;
                ConsoleLogger.printSuccess("Logged in as \'" + employee.getFullName() + "\' (" + employee.getClass().getSimpleName() + ")");
                return;
            }
        }
        ConsoleLogger.printError("Invalid username or passsword");
    }

    public void logout() {
        if (user != null) {
            ConsoleLogger.printSuccess("Logged out from \'" + user.getFullName() + "\' (" + user.getClass().getSimpleName() + ")");
            user = null;
        }
    }

    public void addCashier(Employee data) {
        if (user == null || !user.hasPermission(Action.ADD_CASHIER)) {
            printUnpermittedWarning();
            return;
        }

        if (data == null) {
            ConsoleLogger.printError("Cannot create a null cashier");
            return;
        }

        if (usernameExists(data.getUsername())) {
            ConsoleLogger.printWarning("Username \'" + data.getUsername() + "\' already exists");
            return;
        }

        Cashier temp = new Cashier(data.getFirstName(), data.getLastName(), data.getGender(), data.getEmail(), data.getPhoneNumber(), data.getSalary(), data.getUsername(), data.getPassword(), data.getCreatedBy());
        employees.add(temp);
        ConsoleLogger.printSuccess("Added \'" + temp.getFullName() + "\' (" + temp.getClass().getSimpleName() + ")");
        return;
    }

    public void addWaiter(Employee data) {
        if (user == null || !user.hasPermission(Action.ADD_CASHIER)) {
            printUnpermittedWarning();
            return;
        }

        if (data == null) {
            ConsoleLogger.printError("Cannot create a null waiter");
            return;
        }

        if (usernameExists(data.getUsername())) {
            ConsoleLogger.printWarning("Username \'" + data.getUsername() + "\' already exists");
            return;
        }

        Waiter temp = new Waiter(data.getFirstName(), data.getLastName(), data.getGender(), data.getEmail(), data.getPhoneNumber(), data.getSalary(), data.getUsername(), data.getPassword(), data.getCreatedBy());
        employees.add(temp);
        ConsoleLogger.printSuccess("Added \'" + temp.getFullName() + "\' (" + temp.getClass().getSimpleName() + ")");
        return;
    }

    public void addManager(Employee data) {
        if (user == null || !user.hasPermission(Action.ADD_MANAGER)) {
            printUnpermittedWarning();
            return;
        }

        if (data == null) {
            ConsoleLogger.printError("Cannot create a null manager");
            return;
        }

        if (usernameExists(data.getUsername())) {
            ConsoleLogger.printWarning("Username \'" + data.getUsername() + "\' already exists");
            return;
        }

        Manager temp = new Manager(data.getFirstName(), data.getLastName(), data.getGender(), data.getEmail(), data.getPhoneNumber(), data.getSalary(), data.getUsername(), data.getPassword(), data.getCreatedBy());
        employees.add(temp);
        ConsoleLogger.printSuccess("Added \'" + temp.getFullName() + "\' (" + temp.getClass().getSimpleName() + ")");
        return;
    }

    public void addAdmin(Employee data) {
        if (user == null || !user.hasPermission(Action.ADD_MANAGER)) {
            printUnpermittedWarning();
            return;
        }

        if (data == null) {
            ConsoleLogger.printError("Cannot create a null manager");
            return;
        }

        if (usernameExists(data.getUsername())) {
            ConsoleLogger.printWarning("Username \'" + data.getUsername() + "\' already exists");
            return;
        }

        Admin temp = new Admin(data.getFirstName(), data.getLastName(), data.getGender(), data.getEmail(), data.getPhoneNumber(), data.getSalary(), data.getUsername(), data.getPassword(), data.getCreatedBy());
        employees.add(temp);
        ConsoleLogger.printSuccess("Added \'" + temp.getFullName() + "\' (" + temp.getClass().getSimpleName() + ")");
        return;
    }

    public void addCategory(String name, String desc) {
        Category temp = new Category(name, desc);
        categories.add(temp);
        ConsoleLogger.printSuccess("Added \'" + temp.getName()+ "\'");
    }

    public void addMenuItem(String name, String desc, Category category) {
        if (category == null) {
            ConsoleLogger.printError("Cannot assign null category to menu item.");
            return;
        }

        MenuItem temp = new MenuItem(name, desc, category, true);
        items.add(temp);
        ConsoleLogger.printSuccess("Added \'" + temp.getName()+ "\'");
    }

    public void addSizeOptionToItem(MenuItem item, String label, double price) {
        if (item == null) {
            ConsoleLogger.printError("Cannot add size option to null menu item.");
            return;
        }

        ConsoleLogger.printSuccess("Added \'" + label + "(" + price + ")" + "\' to \'" + item.getName() + "\'");
        item.createPriceOption(label, price);
    }

    public void addTableOrder(TableOrder table_order) {
        if (table_order == null) {
            ConsoleLogger.printError("Cannot add null table order.");
            return;
        }

        ConsoleLogger.printSuccess("Added new table order at table #" + table_order.getTable().getNumber() + ". Assigned to " + table_order.getWaiter().getFullName());
        table_orders.add(table_order);
    }

    public void addTable(int seat_count) {
        if (seat_count <= 0) {
            ConsoleLogger.printError("Table seat count cannot be negative nor empty.");
            return;
        }

        Table temp = new Table(seat_count, true);
        ConsoleLogger.printSuccess("Added table #" + temp.getNumber());
        tables.add(temp);
    }

    /**
     * Updates the first name of the specified employee.
     * @param employee the employee to update the first name of
     * @param firstname the first name to update to
     */
    public void updateEmployeeFirstName(Employee employee, String firstname) {
        ConsoleLogger.printSuccess("Updated first name from \'" + employee.getFirstName() + "\' to \'" + firstname + "\'");
        employee.setFirstName(firstname);
        return;
    }

    /**
     * Updates the last name of the specified employee.
     * @param employee the employee to update the last name of
     * @param lastname the last name to update to
     */
    public void updateEmployeeLastName(Employee employee, String lastname) {
        ConsoleLogger.printSuccess("Updated last name from \'" + employee.getLastName() + "\' to \'" + lastname + "\'");
        employee.setLastName(lastname);
        return;
    }
    
    /**
     * Updates the gender of the specified employee.
     * @param employee the employee to update the gender of
     * @param gender the gender to update to
     */
    public void updateEmployeeGender(Employee employee, String gender) {
        ConsoleLogger.printSuccess("Updated gender from \'" + employee.getGender() + "\' to \'" + gender + "\'");
        employee.setGender(gender);
        return;
    }

    /**
     * Updates the email of the specified employee.
     * @param employee the employee to update the email of
     * @param email the email to update to
     */
    public void updateEmployeeEmail(Employee employee, String email) {
        ConsoleLogger.printSuccess("Updated email from \'" + employee.getEmail() + "\' to \'" + email + "\'");
        employee.setEmail(email);
        return;
    }

    /**
     * Updates the phone number of the specified employee.
     * @param employee the employee to update the phone number of
     * @param phone_number the phone number to update to
     */
    public void updateEmployeePhoneNumber(Employee employee, String phone_number) {
        ConsoleLogger.printSuccess("Updated phone number from \'" + employee.getPhoneNumber() + "\' to \'" + phone_number + "\'");
        employee.setPhoneNumber(phone_number);
        return;
    }

    /**
     * Updates the salary of the specified employee.
     * @param employee the employee to update the salary of
     * @param salary the salary to update to
     */
    public void updateEmployeeSalary(Employee employee, double salary) {
        ConsoleLogger.printSuccess("Updated salary from \'" + employee.getGender() + "\' to \'" + salary + "\'");
        employee.setSalary(salary);
        return;
    }

    /**
     * Updates the password of the specified employee
     * @param employee the employee to update the password of
     * @param password the password to update to
     */
    public void updateEmployeePassword(Employee employee, String password) {
        ConsoleLogger.printSuccess("Updated password from \'" + employee.getPassword() + "\' to \'" + password + "\'");
        employee.setUsername(password);
        return;
    }
    
    /**
     * Updates the username of the specified employee.
     * @param employee the employee to update the username of
     * @param username the username to update to
     */
    public void updateEmployeeUsername(Employee employee, String username) {
        if (usernameExists(username)) {
            ConsoleLogger.printWarning("Username \'" + username + "\' already exists");
            return;
        }
        ConsoleLogger.printSuccess("Updated username from \'" + employee.getUsername() + "\' to \'" + username + "\'");
        employee.setUsername(username);
        return;
    }

    public void updateCategoryName(Category category, String name) {
        ConsoleLogger.printSuccess("Updated name from \'" + category.getName() + "\' to \'" + name + "\'");
        category.setName(name);
        return;
    }

    public void updateCategoryDescription(Category category, String desc) {
        ConsoleLogger.printSuccess("Updated description from \'" + category.getDescription() + "\' to \'" + desc + "\'");
        category.setDescription(desc);
        return;
    }

    public void updateMenuItemName(MenuItem item, String name) {
        ConsoleLogger.printSuccess("Updated name from \'" + item.getName() + "\' to \'" + name + "\'");
        item.setName(name);
    }

    public void updateMenuItemDescription(MenuItem item, String description) {
        ConsoleLogger.printSuccess("Updated description from \'" + item.getDescription() + "\' to \'" + description + "\'");
        item.setDescription(description);
    }

    public void updateMenuItemCategory(MenuItem item, Category category) {
        if (category == null) {
            ConsoleLogger.printError("Cannot assign null category to menu item");
            return;
        }

        ConsoleLogger.printSuccess("Updated category from \'" + item.getCategory().getName() + "\' to \'" + category.getName() + "\'");
        item.setCategory(category);
    }

    public void updateSizeOptionLabel(ItemPriceOption option, String label) {
        if (option == null) {
            ConsoleLogger.printError("Cannot update label of null option");
            return;
        }

        ConsoleLogger.printSuccess("Updated label from \'" + option.getLabel() + "\' to \'" + label + "\'");
        option.setLabel(label);
    }

    public void updateSizeOptionPrice(ItemPriceOption option, double price) {
        if (option == null) {
            ConsoleLogger.printError("Cannot update price of null option");
            return;
        }

        ConsoleLogger.printSuccess("Updated price from \'" + option.getPrice() + "\' to \'" + price + "\'");
        option.setPrice(price);
    }

    public MenuItem getMenuItemByID(int id) {
        for (MenuItem item : items) {
            if (item.getID() == id) { return item; }
        }
        return null;
    }

    public Employee getEmployeeByID(int id) {
        for (Employee employee : employees) {
            if (employee.getID() == id) { return employee; }
        }
        return null;
    }

    public Category getCategoryByID(int id) {
        for (Category category : categories) {
            if (category.getID() == id) { return category; }
        }
        return null;
    }

    public void displayEmployeeList() {
        System.out.println("------Employees (" + (employees.size() - 1) + ")------");
        for (Employee employee : employees) {
            if (!(employee instanceof Admin)) {
                employee.display();
            }
        }
    }

    public void displayMenuItemList() {
        System.out.println("------Menu Items (" + items.size() + ")------");
        for (MenuItem item : items) {
            item.display();
        }
    }

    public void displayCategoryList() {
        System.out.println("------Categories (" + categories.size() + ")------");
        for (Category category : categories) {
            category.display();
        }
    }

    public void displayTableList() {
        System.out.println("------Tables (" + tables.size() + ")------");
        for (Table table : tables) {
            table.display();
        }
    }

    public void displayTableOrders() {
        System.out.println("------Table Orders (" + table_orders.size() + ")------");
        for (TableOrder table_order : table_orders) {
            table_order.display();
        }
    }

    public void printUnpermittedWarning() {
        ConsoleLogger.printWarning("You do not have the permission to perform this action.");
    }

    private boolean usernameExists(String username) {
       for (Employee employee : employees) {
            if (employee.getUsername() == username) { return true; }
        } 
        return false;
    }
}
