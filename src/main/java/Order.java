package src.main.java;

public class Order { 
    private Item item;
    private int quantity;

    public Order(Item item, int quantity) {
        setItem(item);
        setQuantity(quantity);
    }

    @Override
    public String toString() {
        return "Item: " + item + ", Quantity: " + quantity;
    }

    // setters

    public Order setItem(Item item) {
        this.item = item;
        return this;
    }

    public Order setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity must be 0 or higher.");
            this.quantity = 0;
        }
        this.quantity = quantity;
        return this;
    }

    // getters

    public Item getItem() {
        return this.item;
    }

    public int getQuantity() {
        return this.quantity;
    }   

}
