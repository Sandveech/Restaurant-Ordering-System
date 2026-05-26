package src.main.java.com.restaurant;

import java.util.ArrayList;
import java.util.Scanner;

import src.main.java.com.restaurant.enums.Action;
import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.ItemPriceOption;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Table;
import src.main.java.com.restaurant.model.TableOrder;
import src.main.java.com.restaurant.model.Waiter;
import src.main.java.com.restaurant.util.ConsoleLogger;
import src.main.java.com.restaurant.util.ValidationUtils;

public class Main {
    // username: admin
    // username: johns
    // username: johnd
    // username: janed
    // password: 12345678

    // to-do: All the stuff for order management and receipt
    // note: they all have "This option is currently unavailable"

    public static void main(String[] args) {
        RestaurantSystem restaurant = new RestaurantSystem();

        Scanner sc = new Scanner(System.in);    

        while (true) {
            System.out.println("------Restaurant Ordering System------");
            System.out.println("1 - Login");
            System.out.println("2 - Exit");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    String username = promptUsername(sc, "Enter username:");
                    String password = promptPassword(sc, "Enter password:");
                    restaurant.login(username, password);
                    if (restaurant.getUser() != null) { promptMainMenu(restaurant, sc); }
                    break; 
                case 2:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // region Main Menu
    public static void promptMainMenu(RestaurantSystem restaurant, Scanner sc) {
        while (true) {
            System.out.println("------Restaurant Ordering System------");
            System.out.println("1 - Menu Management");
            System.out.println("2 - Table Management");
            System.out.println("3 - Order Management");
            System.out.println("4 - Employee Management");
            System.out.println("5 - User Management");
            System.out.println("6 - View Receipts");
            System.out.println("7 - Logout");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    promptMenuManagement(restaurant, sc);
                    break;
                case 2:
                    promptTableManagement(restaurant, sc);
                    break;
                case 3:
                    promptOrderManagement(restaurant, sc);
                    break;
                case 4:
                    promptEmployeeManagement(restaurant, sc);
                    break;
                case 5:
                    promptUserManagement(restaurant, sc);
                    break;
                case 6:
                    ConsoleLogger.printWarning("This option is currently unavailable.");
                    break;
                case 7:
                    restaurant.logout();
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
    // endregion

    // region Menu Management
    public static void promptMenuManagement(RestaurantSystem restaurant, Scanner sc) {
        Employee user = restaurant.getUser();
        if (user == null || !user.hasPermission(Action.MANAGE_MENU)) {
            restaurant.printUnpermittedWarning();
            return;
        }

        while (true) {
            System.out.println("------Menu Management-----");
            System.out.println("1 - Menu Item");
            System.out.println("2 - Category");
            System.out.println("3 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    promptMenuItemManagement(restaurant, sc);
                    break;
                case 2:
                    promptCategoryManagement(restaurant, sc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void promptMenuItemManagement(RestaurantSystem restaurant, Scanner sc) {
        while (true) {
            System.out.println("------Menu Item Management-----");
            System.out.println("1 - List Menu Items");
            System.out.println("2 - Edit Menu Item");
            System.out.println("3 - Add Menu Item");
            System.out.println("4 - Remove Menu Item");
            System.out.println("5 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    restaurant.displayMenuItemList();
                    break;
                case 2:
                    int id = promptInt(sc, "Enter Menu Item ID:");
                    promptEditMenuItem(restaurant, sc, restaurant.getMenuItemByID(id));
                    break;
                case 3:
                    promptAddMenuItem(restaurant, sc);
                    break;
                case 4:
                    ConsoleLogger.printWarning("This option is currently unavailable.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void promptEditMenuItem(RestaurantSystem restaurant, Scanner sc, MenuItem item) {
        if (item == null) {
            ConsoleLogger.printError("This item does not exist.");
            return;
        }

        while (true) {
            System.out.println("------Edit Menu Item-----");
            System.out.println("1 - Name " + "(" + item.getName() + ")");
            System.out.println("2 - Description " + "(" + item.getDescription() + ")");
            System.out.println("3 - Category " + "(" + item.getCategory().getName() + ")");
            System.out.println("4 - Size Options " + item.formattedSizesString());
            System.out.println("5 - Add Size Option");
            System.out.println("6 - Remove Size Option");
            System.out.println("7 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    String name = promptName(sc, "Enter Name:");
                    restaurant.updateMenuItemName(item, name);
                    break;
                case 2:
                    String desc = promptName(sc, "Enter Description:");
                    restaurant.updateMenuItemDescription(item, desc);
                    break;
                case 3:
                    int id = promptInt(sc, "Enter Category ID:");
                    restaurant.updateMenuItemCategory(item, restaurant.getCategoryByID(id));
                    break;
                case 4:
                    promptEditMenuItemSizeOptions(restaurant, sc, item);
                    break;
                case 5:
                    promptAddSizeOption(restaurant, sc, item);
                    break;
                case 6:
                    ConsoleLogger.printWarning("This option is currently unavailable.");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void promptEditMenuItemSizeOptions(RestaurantSystem restaurant, Scanner sc, MenuItem item) {
        if (item == null) {
            ConsoleLogger.printError("Cannot edit size options of null menu item.");
            return;
        }

        ArrayList<ItemPriceOption> options = item.getItemPriceOption();
        int size = options.size();

        while (true) {
            System.out.println("------Edit Size Options (" + item.getName() + ")-----");
            for (int i = 0; i < size; i++) {
                ItemPriceOption option = options.get(i);
                System.out.println((i + 1) + " - " + option.getLabel() + " (" + option.getPrice() + ")");
            }
            System.out.println((size + 1) + " - " + "Back");

            int choice = promptInt(sc, "Input option:");

            if (choice == size + 1) {
                return;
            }
            else if (choice <= 0 || choice > size + 1) {
                System.out.println("Invalid option.");
            }
            else {
                ItemPriceOption option = options.get(choice - 1);
                promptEditSizeOption(restaurant, sc, item, option);
            }
        }
    }

    public static void promptEditSizeOption(RestaurantSystem restaurant, Scanner sc, MenuItem item, ItemPriceOption option) {
        if (option == null) {
            ConsoleLogger.printError("Cannot edit a null item price option.");
            return;
        }

        while (true) {
            System.out.println("------Edit Item Size Option (" + item.getName() + ")-----");
            System.out.println("1 - Label " + "(" + option.getLabel() + ")");
            System.out.println("2 - Price " + "(" + option.getPrice() + ")");
            System.out.println("3 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    String label = promptString(sc, "Enter Label:");
                    restaurant.updateSizeOptionLabel(option, label);
                    break;
                case 2:
                    double price = promptDouble(sc, "Enter Price:");
                    restaurant.updateSizeOptionPrice(option, price);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            } 
        }
    }

    public static void promptAddSizeOption(RestaurantSystem restaurant, Scanner sc, MenuItem item) {
        if (item == null) {
            ConsoleLogger.printError("Cannot add size option to null menu item.");
            return;
        }

        String label = promptString(sc, "Enter Label:");
        double price = promptDouble(sc, "Enter Price:");        
        restaurant.addSizeOptionToItem(item, label, price);
    }

    public static void promptAddMenuItem(RestaurantSystem restaurant, Scanner sc) {
        System.out.println("------Add Menu Item-----");
        String name = promptString(sc, "Enter Name:");
        String desc = promptString(sc, "Enter Description:");
        Category category = restaurant.getCategoryByID(promptInt(sc, "Enter Category ID:"));

        restaurant.addMenuItem(name, desc, category);
    }

    public static void promptCategoryManagement(RestaurantSystem restaurant, Scanner sc) {
        while (true) {
            System.out.println("------Category Management-----");
            System.out.println("1 - List Categories");
            System.out.println("2 - Edit Category");
            System.out.println("3 - Add Category");
            System.out.println("4 - Remove Category");
            System.out.println("5 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    restaurant.displayCategoryList();
                    break;
                case 2:
                    int id = promptInt(sc, "Enter Category ID:");
                    promptEditCategory(restaurant, sc, restaurant.getCategoryByID(id));
                    break;
                case 3:
                    promptAddCategory(restaurant, sc);
                    break;
                case 4:
                    ConsoleLogger.printWarning("This option is currently unavailable.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void promptEditCategory(RestaurantSystem restaurant, Scanner sc, Category category) {
        if (category == null) {
            ConsoleLogger.printError("This category does not exist.");
            return;
        }

        while (true) {
            System.out.println("------Edit Category-----");
            System.out.println("1 - Name " + "(" + category.getName() + ")");
            System.out.println("2 - Description " + "(" + category.getDescription() + ")");
            System.out.println("3 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    String name = promptName(sc, "Enter Name:");
                    restaurant.updateCategoryName(category, name);
                    break;
                case 2:
                    String desc = promptName(sc, "Enter Description:");
                    restaurant.updateCategoryDescription(category, desc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void promptAddCategory(RestaurantSystem restaurant, Scanner sc) {
        System.out.println("------Add Category-----");
        String name = promptString(sc, "Enter Name:");
        String desc = promptString(sc, "Enter Description:");
        restaurant.addCategory(name, desc);
    }
    // endregion

    // region Table Management
    public static void promptTableManagement(RestaurantSystem restaurant, Scanner sc) {
        Employee user = restaurant.getUser();
        if (user == null || !user.hasPermission(Action.MANAGE_TABLES)) {
            restaurant.printUnpermittedWarning();
            return;
        }

        while (true) {
            System.out.println("------Table Management------");
            System.out.println("1 - List Tables");
            System.out.println("2 - Manage Reservations");
            System.out.println("3 - Table Assignment");
            System.out.println("4 - Add Table");
            System.out.println("5 - Remove Table");
            System.out.println("6 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    restaurant.displayTableList();
                    break;
                case 2:
                    promptTableReservationsManagement(restaurant, sc);
                    break;
                case 3:
                    promptTableAssignment(restaurant, sc);
                    break;
                case 4:
                    promptAddTable(restaurant, sc);
                    break;
                case 5:
                    ConsoleLogger.printWarning("This option is currently unavailable.");
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void promptTableReservationsManagement(RestaurantSystem restaurant, Scanner sc) {
        ArrayList<Table> tables = new ArrayList<>();

        for (Table table : restaurant.getTables()) {
            if (!table.isOccupied() && table.isActive()) { tables.add(table); }
        }

        int size = tables.size();

        while (true) {
            System.out.println("------Table Reservation Management------");
            if (size == 0) {
                System.out.println("No tables available.");
                return;
            }

            for (int i = 0; i < size; i++) {
                Table table = tables.get(i);
                System.out.print((i + 1) + " - Table #" + table.getNumber());
                if (table.isReserved()) { System.out.println(" (Reservered)"); }
                else { System.out.println(); }
                System.out.println("    + Seat Count: " + table.getSeatCount());
            }
            System.out.println((size + 1) + " - Back");

            int choice = promptInt(sc, "Enter Choice:");

            if (choice == size + 1) {
                return;
            }
            else if (choice <= 0 || choice > size + 1) {
                System.out.println("Invalid option.");
            }
            else {
                Table table = tables.get(choice - 1);
                table.setReserved(!table.isReserved());
            }
        }
    }

    public static void promptTableAssignment(RestaurantSystem restaurant, Scanner sc) {
        Employee user = restaurant.getUser();
        if (user == null || !(user instanceof Waiter) || !user.hasPermission(Action.ASSIGN_TABLES)) {
            restaurant.printUnpermittedWarning();
            return;
        }

        while (true) {
            ArrayList<Table> tables = new ArrayList<>();

            for (Table table : restaurant.getTables()) {
                if (!table.isOccupied() && table.isActive() && !table.isReserved()) { tables.add(table); }
            }

            int size = tables.size();

            System.out.println("------Table Assignment------");
            if (size == 0) {
                System.out.println("No tables available.");
                return;
            }

            for (int i = 0; i < size; i++) {
                Table table = tables.get(i);
                System.out.println((i + 1) + " - Table #" + table.getNumber());
                System.out.println("    + Seat Count: " + table.getSeatCount());
            }
            System.out.println((size + 1) + " - Back");

            int choice = promptInt(sc, "Enter Choice:");

            if (choice == size + 1) {
                return;
            }
            else if (choice <= 0 || choice > size + 1) {
                System.out.println("Invalid option.");
            }
            else {
                Table table = tables.get(choice - 1);
                Waiter waiter = (Waiter) user;
                TableOrder table_order = waiter.createTableOrder(table);
                restaurant.addTableOrder(table_order);
            }
        }
    }

    public static void promptAddTable(RestaurantSystem restaurant, Scanner sc) {
        Employee user = restaurant.getUser();
        if (user == null || !user.hasPermission(Action.ADD_TABLE)) {
            restaurant.printUnpermittedWarning();
            return;
        }

        int seat_count = promptInt(sc, "Enter Seat Count:");
        restaurant.addTable(seat_count);
    }

    // endregion

    // region Order Management
    public static void promptOrderManagement(RestaurantSystem restaurant, Scanner sc) {
        ArrayList<TableOrder> table_orders = new ArrayList<>();
        for (TableOrder table_order : restaurant.getTableOrders()) {

        } 

        while (true) {
            System.out.println("------Order Management------");
            System.out.println("1 - List Orders");
            System.out.println("2 - Take Order");
            System.out.println("3 - Complete Order");
            System.out.println("4 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    ConsoleLogger.printWarning("This option is currently unavailable.");
                    //restaurant.displayTableOrders();
                    break;
                case 2:
                    ConsoleLogger.printWarning("This option is currently unavailable.");
                    break;
                case 3:
                    ConsoleLogger.printWarning("This option is currently unavailable.");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
    // endregion

    // region Employee Management
    public static void promptEmployeeManagement(RestaurantSystem restaurant, Scanner sc) {
        Employee user = restaurant.getUser();

        if (user == null || (!user.hasPermission(Action.MANAGE_USER) && !user.hasPermission(Action.MANAGE_EMPLOYEE))) {
            restaurant.printUnpermittedWarning();
            return;
        }

        while (true) {
            System.out.println("------Employee Management-----");
            System.out.println("1 - List Employees");
            System.out.println("2 - Edit Employee");
            System.out.println("3 - Add Employee");
            System.out.println("4 - Remove Employee");
            System.out.println("5 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    restaurant.displayEmployeeList();
                    break;
                case 2:
                    int id = promptInt(sc, "Enter Employee ID:");
                    promptEditEmployee(restaurant, sc, restaurant.getEmployeeByID(id));
                    break;
                case 3:
                    promptAddEmployee(restaurant, sc);
                    break;
                case 4:
                    promptRemoveEmployee(restaurant, sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void promptAddEmployee(RestaurantSystem restaurant, Scanner sc) {
        while (true) {
            System.out.println("------Add Employee-----");
            System.out.println("1 - Cashier");
            System.out.println("2 - Waiter");
            System.out.println("3 - Manager");
            System.out.println("4 - Admin");
            System.out.println("5 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    handleEmployeeAdd(restaurant, sc, Action.ADD_CASHIER);
                    break;
                case 2:
                    handleEmployeeAdd(restaurant, sc, Action.ADD_WAITER);
                    break;
                case 3:
                    handleEmployeeAdd(restaurant, sc, Action.ADD_MANAGER);
                    break;
                case 4:
                    handleEmployeeAdd(restaurant, sc, Action.ADD_ADMIN);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void promptEditEmployee(RestaurantSystem restaurant, Scanner sc, Employee employee) {
        if (employee == null) {
            ConsoleLogger.printError("This employee does not exist.");
            return;
        }

        while (true) {
            System.out.println("------Edit Employee-----");
            System.out.println("1 - First Name " + "(" + employee.getFirstName() + ")");
            System.out.println("2 - Lat Name " + "(" + employee.getLastName() + ")");
            System.out.println("3 - Gender " + "(" + employee.getGender() + ")");
            System.out.println("4 - Email " + "(" + employee.getEmail() + ")");
            System.out.println("5 - Phone Number " + "(" + employee.getPhoneNumber() + ")");
            System.out.println("6 - Username " + "(" + employee.getUsername() + ")");
            System.out.println("7 - Password " + "(" + employee.getPassword() + ")");
            System.out.println("8 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    String fname = promptName(sc, "Enter First Name:");
                    restaurant.updateEmployeeFirstName(employee, fname);
                    break;
                case 2:
                    String lname = promptName(sc, "Enter Last Name:");
                    restaurant.updateEmployeeLastName(employee, lname);
                    break;
                case 3:
                    String gender = promptName(sc, "Enter Gender:");
                    restaurant.updateEmployeeGender(employee, gender);
                    break;
                case 4:
                    String email = promptName(sc, "Enter Email:");
                    restaurant.updateEmployeeEmail(employee, email);
                    break;
                case 5:
                    String phone = promptName(sc, "Enter Phone Number:");
                    restaurant.updateEmployeePhoneNumber(employee, phone);
                    break;
                case 6:
                    String uname = promptName(sc, "Enter Username:");
                    restaurant.updateEmployeeUsername(employee, uname);
                    break;
                case 7:
                    String pass = promptName(sc, "Enter Password:");
                    restaurant.updateEmployeePassword(employee, pass);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void handleEmployeeAdd(RestaurantSystem restaurant, Scanner sc, Action action) {
        String fname = promptName(sc, "Enter First Name:");
        String lname = promptName(sc, "Enter Last Name:");
        String gender = promptString(sc, "Enter Gender");
        String email = promptEmail(sc, "Enter Email:");
        String phone = promptPhoneNumber(sc, "Enter Phone Number:");
        double salary = promptDouble(sc, "Enter Salary:");
        String uname = promptUsername(sc, "Enter Username:");
        String pass = promptPassword(sc, "Enter Password:");

        Employee data = new Employee(fname, lname, gender, email, phone, salary, uname, pass, restaurant.getUser());
        switch (action) {
            case Action.ADD_CASHIER:
                restaurant.addCashier(data);
                return;
            case Action.ADD_WAITER:
                restaurant.addWaiter(data);
                return;
            case Action.ADD_MANAGER:
                restaurant.addManager(data);
                return;
            case Action.ADD_ADMIN:
                restaurant.addAdmin(data);
                return;
            default:
                return;
        }
    }

    public static void promptRemoveEmployee(RestaurantSystem restaurant, Scanner sc) {
        ConsoleLogger.printWarning("This option is currently unavailable.");
    }
    // endregion

    // region Account Management
    public static void promptUserManagement(RestaurantSystem restaurant, Scanner sc) {
        while (true) {
            System.out.println("------User Management------");
            System.out.println("1 - Show Information");
            System.out.println("2 - Edit Information");
            System.out.println("3 - Back");
            int choice = promptInt(sc, "Input option:");

            switch (choice) {
                case 1:
                    restaurant.getUser().display();
                    break;
                case 2:
                    promptEditEmployee(restaurant, sc, restaurant.getUser());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
    // endregion

    // region Input Prompts
    public static String promptString(Scanner sc, String message) {
        System.out.println(message);
        System.out.print("> ");
        return sc.nextLine();
    }

    public static String promptName(Scanner sc, String message) {
        String name;
        do {
            name = promptString(sc, message);
            if (ValidationUtils.isValidName(name)) { return name; }
            ConsoleLogger.printWarning("Invalid name. Try again (No spaces and special characters)");
        } while (true);
    }

    public static String promptEmail(Scanner sc, String message) {
        String email;
        do {
            email = promptString(sc, message);
            if (ValidationUtils.isValidEmail(email)) { return email; }
            ConsoleLogger.printWarning("Invalid email. Try again");
        } while (true);
    }

    public static String promptPhoneNumber(Scanner sc, String message) {
        String phone;
        do {
            phone = promptString(sc, message);
            if (ValidationUtils.isValidPhoneNumber(phone)) { return phone; }
            ConsoleLogger.printWarning("Invalid phone number. Try again");
        } while (true);
    }

    public static String promptUsername(Scanner sc, String message) {
        String uname;
        do {
            uname = promptString(sc, message);
            if (ValidationUtils.isValidUsername(uname)) { return uname; }
            ConsoleLogger.printWarning("Invalid username. Try again (No spaces)");
        } while (true);
    }

    public static String promptPassword(Scanner sc, String message) {
        String password;
        do  {
            password = promptString(sc, message);
            if (ValidationUtils.isValidPassword(password)) { return password; }
            ConsoleLogger.printWarning("Invalid password. Try again (Minimum 8 characters)");
        } while (true);
    }

    public static int promptInt(Scanner sc, String message) {
        System.out.println(message);
        System.out.print("> ");
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    public static double promptDouble(Scanner sc, String message) {
        System.out.println(message);
        System.out.print("> ");
        double n = sc.nextDouble();
        sc.nextLine();
        return n;
    }
    // endregion
}
