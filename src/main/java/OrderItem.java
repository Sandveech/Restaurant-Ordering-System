package src.main.java;

public class OrderItem {
    private int id;
    private MenuItem item;
    private int quantity;
    private char size;

    public OrderItem(int id, MenuItem item, int quantity, char size) {
        setId(id).setItem(item).setQuantity(quantity).setSize(size);
    }

    @Override
    public String toString() {
        return "";
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
}
