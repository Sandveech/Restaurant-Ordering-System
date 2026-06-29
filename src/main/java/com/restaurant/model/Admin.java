package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Admin extends Employee {
    private static int adminCount = 0;

    public Admin(String firstName, String lastName, String gender, String email, String phoneNumber, double salary, String username, String password, Employee createdBy) {
        super(firstName, lastName, gender, email, phoneNumber, salary, username, password, createdBy);
    }

    /**
     * Returns the historical count of admins.
     * @return the historical count of admins
     */
    private static int getAdminCount() { return adminCount; }

    /**
     * Returns a value indicating whether this admin has the specified permission.
     * @param permission the permission to check for
     * @return {@true} if the admin has the specified permission; otherwise, {@false}
     */
    @Override
    public boolean hasPermission(Action permission) {
        return true;
    }

    @Override
    public void work() {
        if (isActive()) { 
            System.out.println("is currently working, and is doing admin work.");
            return;
        }

        System.out.println("is not currently working");
    }
}