package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Cashier extends Employee {
    private static int cashierCount = 0;
    private static double startingBalance = 0;
    private static double currentBalance = 0;

    public Cashier(String firstName, String lastName, String gender, String email, String phoneNumber, double salary, String username, String password, Employee createdBy) {
        super(firstName, lastName, gender, email, phoneNumber, salary, username, password, createdBy);
    }

    /**
     * Returns the total count of cashiers.
     * @return the total count of cashiers.
     */
    public static int getCashierCount() { return cashierCount; }

    // getters and setters
    public double getStartingBalance() {
        return startingBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setStartingBalance(double balance) {
        startingBalance = Math.max(0, balance);
    } 

    public void setCurrentBalance(double balance) {
        currentBalance = Math.max(0, balance);
    } 

    @Override
    public void work() {
        if (isActive()) { 
            System.out.println("is currently working, and has gained " + currentBalance + " today.");
            return;
        }

        System.out.println("is not currently working");
    }
}
