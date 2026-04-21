package src.model;

public class OrderItem {
    private static int count = 0;
    private int id;
    private MenuItem item;
    private int quantity;
    private char size;
    private Cart cart;

    public OrderItem(MenuItem item, int quantity, char size, Cart cart) {
        setId(count++);
        setItem(item);
        setQuantity(quantity);
        setSize(size);
        setCart(cart);
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Quantity: " + getQuantity() + "x";
    }

    // setters
    private void setId(int id) {
        if (id >= 0) { this.id = id; }
    }

    private void setItem(MenuItem item) {
        if (item != null) { this.item = item; }
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) { this.quantity = quantity; }
    }

    public void setSize(char size) {
        this.size = size;
    }

    private void setCart(Cart cart) {
        if (cart != null) { this.cart = cart; }
    }

    // getters
    private int getCount() {
        return count;
    }
    
    private int getId() {
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

    public Cart getCart() {
        return cart;
    }

    // utilities
    public double calculateTotalCost() {
        return (item != null) ? item.getPrice() * quantity : 0;
    }
}
