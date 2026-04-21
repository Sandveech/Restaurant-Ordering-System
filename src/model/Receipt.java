package src.model;

public class Receipt {
    private static int count = 0;
    private int id; 
    private Table table;
    private Cart cart;
    private Employee cashier; 
    private double raw_cost;
    private double discount_percentage;
    private double tax_percentage;

    public Receipt(Table table, Cart cart, Employee cashier, double raw_cost, double discount_percentage, double tax_percentage) {
        setId(count++);
        setTable(table);
        setCart(cart);
        setCashier(cashier);
        setRawCost(raw_cost);
        setDiscountPercentage(discount_percentage);
        setTaxPercentage(tax_percentage);
    }

    // setters
    private void setId(int id) {
        if (id >= 0) { this.id = id; }
    }

    private void setTable(Table table) {
        if (table != null) { this.table = table; }
    }

    private void setCart(Cart cart) {
        if (cart != null) { this.cart = cart; }
    }

    private void setCashier(Employee cashier) {
        if (cashier != null) { this.cashier = cashier; }
    }

    public void setRawCost(double raw_cost) {
        this.raw_cost = (raw_cost >= 0) ? raw_cost : 0;
    }

    public void setDiscountPercentage(double amount) {
        this.discount_percentage = (amount >= 0 && amount <= 100) ? amount : 0;
    }

    public void setTaxPercentage(double amount) {
        this.tax_percentage = (amount >= 0) ? amount : 0;
    }

    // getters
    private int getCount() {
        return count;
    }

    private int getId() {
        return id;
    }

    public Table getTable() {
        return table;
    }

    public Cart getCart() {
        return cart;
    }

    public Employee getCashier() {
        return cashier;
    }

    // utilities
}