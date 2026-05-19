package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.enums.Permission;

public class Cashier extends Employee {
    private static int cashier_count = 0;

    public Cashier(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password);
    }

    /**
     * Returns the total count of cashiers.
     * @return the total count of cashiers.
     */
    public static int getCashierCount() { return cashier_count; }
    
    public Receipt generateReceipt(TableOrder table_order, double tax_percentage, String payment_method) {
        if (table_order == null) {
            System.out.println("Cashier cannot generate a receipt without a table order.");
            return null;
        }
        if (!table_order.isCompleted()) {
            System.out.println("Receipt can only be generated after the table order is completed.");
            return null;
        }
        return new Receipt(table_order, this, tax_percentage, payment_method);
    }
}
