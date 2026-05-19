package src.main.java.com.restaurant.model;

public class Waiter extends Employee {
    private static int waiter_count = 0;

    public Waiter(String first_name, String last_name, String gender, String email, String phone_number, double salary, String username, String password) {
        super(first_name, last_name, gender, email, phone_number, salary, username, password);
    }

    /**
     * Creates a new table order for the specified table.
     * @param table the table to take the order of
     * @return the table order of the specified table
     */
    public TableOrder createTableOrder(Table table) {
        if (table == null) {
            System.out.println("Watier cannot create an order without a table.");
            return null;
        }
        if (table.isOccupied()) {
            System.out.println("Table " + table.getNumber() + "is already occupied.");
            return null;
        }

        table.occupy();

        if (!table.isOccupied()) {
            System.out.println("Table " + table.getNumber() + " cannot be occupied.");
            return null;
        }

        return new TableOrder(this, table);
    }

    /**
     * Returns the historical count of waiters.
     * @return the historical count of watiers
     */
    private static int getWaiterCount() { return waiter_count; }
}
