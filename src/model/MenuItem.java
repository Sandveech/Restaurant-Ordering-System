package src.model;

import src.utilities.GeneralUtils;
import src.config.MenuItemConfig;

public class MenuItem {
    private static int count = 0;
    private int id;
    private double price;
    private String name;
    private String description;
    private String category;

    public MenuItem(double price, String name, String description, String category) {
        setId(count++);
        setPrice(price);
        setName(name);
        setDescription(description);
        setCategory(category);
    }

    // setters
    private void setId(int id) {
        if (id >= 0) { this.id = id; }
    }

    public void setPrice(double price) {
        this.price = (price >= MenuItemConfig.MIN_PRICE) ? price : MenuItemConfig.MIN_PRICE;
    }

    public void setName(String name) {
        this.name = GeneralUtils.isValidTextInput(name) ? name : "Menu item #" + id;
    }

    public void setDescription(String description) {
        this.description = GeneralUtils.isValidTextInput(description) ? description : "No description.";
    }

    public void setCategory(String category) {
        this.category = GeneralUtils.isValidTextInput(category) ? category  : "No category";
    }

    // getters
    private int getCount() {
        return count;
    }

    private int getId() {
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
