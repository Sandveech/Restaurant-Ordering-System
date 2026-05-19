package src.main.java.com.restaurant;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RestaurantSystem restaurant_system = new RestaurantSystem();

        Scanner sc = new Scanner(System.in);    

        while (true) {
            System.out.println("1 - Login");
            System.out.println("2 - Exit");
            System.out.print("> ");
            int choice = promptOption(sc);

            switch (choice) {
                case 1:
                    System.out.println("Input username:");
                    System.out.print("> ");
                    String username = sc.nextLine();
                    System.out.println("Input password:");
                    System.out.print("> ");
                    String password = sc.nextLine();
                    restaurant_system.login(username, password);
                case 2:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        // sample data
        // HashMap<String, Category> categories = SampleData.getCategories();
        // ArrayList<MenuItem> menu = SampleData.getMenuItems();
        // ArrayList<Table> tables = SampleData.getTables();
        // ArrayList<Employee> employees = SampleData.getEmployees();

        // Waiter waiter = (Waiter) employees.getFirst();
        // TableOrder table_order = waiter.createTableOrder(tables.getFirst());
        // table_order.addOrder(menu.get(0), 1, "S");
        // table_order.addOrder(menu.get(1), 1, "S");
        // table_order.addOrder(menu.get(0), 3, "S");
        // table_order.addOrder(menu.get(0), 1, "M");
        // table_order.addOrder(menu.get(1), 3, "L");
        // table_order.complete();

        // Cashier cashier = (Cashier) employees.get(1);
        // Receipt receipt = cashier.generateReceipt(table_order, RestaurantConfig.getInstance().getTaxPercentage(), "Cash");

        // if (receipt != null) { receipt.print(); }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int promptOption(Scanner sc) {
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
}
