package src.main.java;

public class Item {
    private double price;
    private String name;
    private String category;

    public Item(double price, String name, String category) {
        setPrice(price);
        setName(name);
        setCategory(category);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", " + "Category: " + category + ", Price: $" + price;
    }

    // setters

    public Item setPrice(double price) {
        if (price < 0) {
            System.out.println("Price must be $0.00 or higher.");
            this.price = 0;
        }
        this.price = price;
        return this;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Item setCategory(String category) {
        this.category = category;
        return this;
    }

    // getters

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }
}
