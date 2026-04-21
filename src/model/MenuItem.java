package src.model;

public class MenuItem {
    private static int count = 0;
    private int id;
    private double price;
    private String name;
    private String description;
    private String category;

    public MenuItem(double price, String name, String description, String category) {
        setId(count++).setPrice(price).setName(name).setDescription(description).setCategory(category);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", " + "Category: " + category + ", Price: $" + price;
    }

    // setters

    private MenuItem setId(int id) {
        if (id >= 0) { this.id = id; }
        return this;
    }

    public MenuItem setPrice(double price) {
        if (price < 0) {
            System.out.println("Price must be $0.00 or higher.");
            this.price = 0;
        }
        this.price = price;
        return this;
    }

    public MenuItem setName(String name) {
        this.name = (name == null || !name.isEmpty() || !name.isBlank()) ? name : "Unknown";
        return this;
    }

    public MenuItem setDescription(String description) {
        this.description = description;
        return this;
    }

    public MenuItem setCategory(String category) {
        this.category = category;
        return this;
    }

    // getters
    public int getCount() {
        return count;
    }

    public int getId() {
        return this.id;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCategory() {
        return this.category;
    }

    // utilities
}
