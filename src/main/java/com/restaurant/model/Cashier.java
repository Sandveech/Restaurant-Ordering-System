package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Cashier extends Employee {
    private static int cashier_count = 0;
    private static double starting_balance = 0;
    private static double current_balance = 0;

    public Cashier(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password, Employee created_by) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password, created_by);
    }

    /**
     * Returns the total count of cashiers.
     * @return the total count of cashiers.
     */
    public static int getCashierCount() { return cashier_count; }

    // getters and setters
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

    @Override
    public void work() {
        if (isActive()) { 
            System.out.println("- is currently working, and has gained " + current_balance + " today.");
            return;
        }

        System.out.println("- is not currently working");
    }
}
