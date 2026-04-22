package src.model;

import src.util.ValidationUtils;
import src.config.AppConstants;

public class MenuItem {
    // fields
    private static int count = 0;
    private int id;
    private String name;
    private double price;

    // constructor
    public MenuItem(String name, double price) {
        setID(count++);

    }

    // getters and setters
    private int getCount() { return count; }
    private int getID() { return id; }
    private String getName() { return name; }
    private double getPrice() { return price; }

    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : count - 1;
    }

    private void setName(String name) {
        this.name = (ValidationUtils.isValidName(name)) ? name : "Unnamed Item";
    }

    private void setPrice(double price) {
        this.price = (ValidationUtils.isValidPrice(price)) ? price : AppConstants.MIN_PRICE();
    }

    // helper functions
}
