package src.main.java.com.restaurant.model;

public class Manager extends Employee {
    private static int manager_count = 0;

    public Manager(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password);


    }

    /**
     * Returns the historical count of managers.
     * @return the historical count of managers
     */
    public static int getManagerCount() { return manager_count; }

    public Boolean approveMenuItem(MenuItem item) {
        if (item == null) {
            System.out.println("Manager cannot approve a null menu item.");
            return false;
        } 

        item.setActive(true);
        System.out.println(getFullName() + " approved menu item: \'" + item.getName() + "\'");
        return true;
    }
}
