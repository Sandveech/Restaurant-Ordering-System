package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Permission;

public class Admin extends Employee {
    private static int admin_count = 0;

    public Admin(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password);
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
    public boolean hasPermission(Permission permission) {
        return true;
    }
}
