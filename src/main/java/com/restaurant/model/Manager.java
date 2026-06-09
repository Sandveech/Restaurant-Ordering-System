package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Manager extends Employee {
    private static int manager_count = 0;

    public Manager(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password, Employee created_by) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password, created_by);

        permissions.add(Action.ADD_CASHIER);
        permissions.add(Action.ADD_WAITER);
        permissions.add(Action.MANAGE_TABLES);
        permissions.add(Action.ADD_TABLE);
    }

    /**
     * Returns the historical count of managers.
     * @return the historical count of managers
     */
    private static int getManagerCount() { return manager_count; }

    @Override
    public void work() {
        if (isActive()) { 
            System.out.println("- is currently working, and is managing the restaurant.");
            return;
        }

        System.out.println("- is not currently working");
    }
}
