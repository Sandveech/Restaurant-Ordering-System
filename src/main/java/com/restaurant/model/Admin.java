package src.main.java.com.restaurant.model;

public class Admin extends Employee {
    private static int admin_count = 0;

    public Admin(String first_name, String last_name, String gender, String email, String phone_number, double salary) {
        super(first_name, last_name, gender, email, phone_number, salary);
    }

    /**
     * Returns the historical count of admins.
     * @return the historical count of admins
     */
    private static int getAdminCount() { return admin_count; }
}
