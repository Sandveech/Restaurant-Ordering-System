package src.main.java;

public class OrderItem {
    private static int id_counter = 0;
    private int id = 0;
    private MenuItem item;
    private int quantity;
    private char size;

    public OrderItem(MenuItem item, int quantity, char size) {
        setId(id_counter++).setItem(item).setQuantity(quantity).setSize(size);
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Quantity: " + getQuantity();
    }

    // setters
    private OrderItem setId(int id) {
        this.id = id;
        return this;
    }

    private OrderItem setItem(MenuItem item) {
        this.item = item;
        return this;
    }

    public OrderItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderItem  setSize(char size) {
        this.size = size;
        return this;
    }

    // getters
    public int getId() {
        return id;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public char getSize() {
        return size;
    }

    // utilities
    public double calculateTotal() {
        return (item != null) ? item.getPrice() * quantity : 0;
    }
}
