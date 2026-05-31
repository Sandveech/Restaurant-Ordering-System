package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Waiter extends Employee {
    private static int waiter_count = 0;
    private static double starting_balance = 0;
    private static double current_balance = 0;

    public Waiter(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password, Employee created_by) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password, created_by);

        permissions.add(Action.MANAGE_TABLES);
        permissions.add(Action.ASSIGN_TABLES);
    }

    public double getStartingBalance() {
        return starting_balance;
    }

    public double getCurrentBalance() {
        return current_balance;
    }

    public void setStartingBalance(double balance) {
        this.starting_balance = Math.max(0, balance);
    } 

    public void setCurrentBalance(double balance) {
        this.current_balance = Math.max(0, balance);
    } 

    /**
     * Returns the historical count of waiters.
     * @return the historical count of watiers
     */
    private static int getWaiterCount() { return waiter_count; }
}
