package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Cashier extends Employee {
    private static int cashier_count = 0;
    private int tables_served_historic = 0;
    private int tables_served_today = 0;

    public Cashier(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password, Employee created_by) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password, created_by);
    }

    /**
     * Returns the total count of cashiers.
     * @return the total count of cashiers.
     */
    public static int getCashierCount() { return cashier_count; }

    // getters and setters
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
}
