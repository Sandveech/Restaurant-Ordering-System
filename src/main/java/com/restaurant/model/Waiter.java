package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Waiter extends Employee {
    private static int waiterCount = 0;
    private int tablesServedHistoric = 0;
    private int tablesServedToday = 0;

    public Waiter(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password, Employee created_by) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password, created_by);

        permissions.add(Action.MANAGE_TABLES);
        permissions.add(Action.ASSIGN_TABLES);
    }

    public int getTablesServedHistoric() {
        return tablesServedHistoric;
    }

    public int getTablesServedToday() {
        return tablesServedToday;
    }

    public void setTablesServedHistoric(int n) {
        this.tablesServedHistoric = Math.max(0, n);
    }

    public void setTablesServedToday(int n) {
        this.tablesServedToday = Math.max(0, n);
    }

    /**
     * Returns the historical count of waiters.
     * @return the historical count of watiers
     */
    private static int getWaiterCount() { return waiterCount; }

    @Override
    public void work() {
        if (isActive()) { 
            System.out.println("is currently working, and has served " + tablesServedToday + " tables today.");
            return;
        }

        System.out.println("is not currently working");
    }
}
