package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Waiter extends Employee {
    private static int waiter_count = 0;
    private int tables_served_historic = 0;
    private int tables_served_today = 0;

    public Waiter(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password, Employee created_by) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password, created_by);

        permissions.add(Action.MANAGE_TABLES);
        permissions.add(Action.ASSIGN_TABLES);
    }

    public int getTablesServedHistoric() {
        return tables_served_historic;
    }

    public int getTablesServedToday() {
        return tables_served_today;
    }

    public void setTablesServedHistoric(int n) {
        this.tables_served_historic = Math.max(0, n);
    }

    public void setTablesServedToday(int n) {
        this.tables_served_today = Math.max(0, n);
    }

    /**
     * Returns the historical count of waiters.
     * @return the historical count of watiers
     */
    private static int getWaiterCount() { return waiter_count; }

    @Override
    public void work() {
        if (isActive()) { 
            System.out.println("- is currently working, and has served " + tables_served_today + " tables today.");
            return;
        }

        System.out.println("- is not currently working");
    }
}
