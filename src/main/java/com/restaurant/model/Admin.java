package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Action;

public class Admin extends Employee {
    private static int admin_count = 0;

    public Admin(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password, Employee created_by) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password, created_by);
    }

    /**
     * Returns the historical count of admins.
     * @return the historical count of admins
     */
    private static int getAdminCount() { return admin_count; }

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
            System.out.println("- is currently working, and is doing admin work.");
            return;
        }

        System.out.println("- is not currently working");
    }
}