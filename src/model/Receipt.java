package src.model;

public class Receipt {
    private static int count = 0;
    private int id; 
    private Table table;
    private Cart cart;
    private Employee waiter; 

    public Receipt(Table table, Cart cart, Employee waiter) {
        setId(count++).setTable(table).setCart(cart).setWaiter(waiter);
    }

    // setters
    private Receipt setId(int id) {
        if (id >= 0) { this.id = id; }
        return this;
    }

    private Receipt setTable(Table table) {
        if (table != null) { this.table = table; }
        return this;
    }

    private Receipt setCart(Cart cart) {
        if (cart != null) { this.cart = cart; }
        return this;
    }

    private Receipt setWaiter(Employee waiter) {
        if (waiter != null) { this.waiter = waiter; }
        return this;
    }

    // getters
    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public Table getTable() {
        return table;
    }

    public Cart getCart() {
        return cart;
    }

    public Employee getWaiter() {
        return waiter;
    }

    // utilities
    public Receipt printDetails() {
        int table_number = (table != null) ? table.getNumber() : -1;
        String waiter_name = (waiter != null) ? waiter.getName() : "Unknown";

        System.out.println("Receipt: #" + id);
        System.out.println("Table: #" + table_number);
        System.out.println("Waiter: " + waiter_name);

        if (cart != null) {
            for (OrderItem order : cart.getOrders()) {
                MenuItem item = order.getItem();
                if (item == null) {continue; }

                System.out.println("Item: " + item.getName() + ", Quantity: " + order.getQuantity() + "x, Size: " + order.getSize() + " | $" + order.calculateTotalCost());
            }

            System.out.println("Total: $" + cart.calculateTotalCost(0));
        }

        return this;
    }
}