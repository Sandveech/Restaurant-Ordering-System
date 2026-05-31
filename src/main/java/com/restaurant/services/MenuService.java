package src.main.java.com.restaurant.services;

import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.repository.RestaurantDataStore;
import src.main.java.com.restaurant.util.ValidationUtils;

public class MenuService {
    private final RestaurantDataStore data;

    public MenuService(RestaurantDataStore data) {
        this.data = data;
    }

    public void addMenuItem(MenuItem item) {
        if (!isValidMenuItem(item)) { return; }

        data.getMenuItems().add(item);
    }

    public void addCategory(Category category) {
        if (!isValidCategory(category)) { return; }

        data.getCategories().add(category);
    }

    public void addCategory(String name, String description) {
        if (!ValidationUtils.isValidText(name)) {
            System.out.println("invalid menu item name.");
            return;
        }
        if (ValidationUtils.isValidText(description)) {
            System.out.println("null menu item.");
            return;
        }

        Category category = new Category(name, description);
        data.getCategories().add(category);    
    }

    public void addCategory(String name) {
        if (!ValidationUtils.isValidText(name)) {
            System.out.println("invalid menu item name.");
            return;
        }

        Category category = new Category(name);
        data.getCategories().add(category);  
    }

    public void approveMenuItem(MenuItem item) {
        if (!isValidMenuItem(item)) { return; }

        item.activate();
    }

    public void approveMenuItem(int id) {
        MenuItem item = searchMenuItemByID(id);
        if (item == null) { return; }

        item.activate();
    }

    public void disapproveMenuItem(MenuItem item) {
        if (!isValidMenuItem(item)) { return; }

        item.deactivate();
    } 

    public void disapproveMenuItem(int id) {
        MenuItem item = searchMenuItemByID(id);
        if (item == null) { return; }

        item.deactivate();
    }

    public MenuItem searchMenuItemByID(int id) {
        if (!isValidMenuItemID(id)) { return null; }

        for (MenuItem item : data.getMenuItems()) {
            if (item.getID() == id) { return item; }
        }

        return null;
    }

    public Category searchCategoryByID(int id) {
        if (!isValidCategoryID(id)) { return null; }

        for (Category category : data.getCategories()) {
            if (category.getID() == id) { return category; }
        }

        return null;
    }
 
    private boolean isValidMenuItem(MenuItem item) {
        if (item == null) {
            System.out.println("null menu item.");
            return false;
        }

        return true;
    }

    private boolean isValidCategory(Category category) {
        if (category == null) {
            System.out.println("null category.");
            return false;
        }

        return true;
    }

    private boolean isValidMenuItemID(int id) {
        if (!ValidationUtils.isValidID(id) || id >= data.getMenuItems().size()) {
            System.out.println("Menu item ID out of range.");
            return false;
        }

        return true;
    }

    private boolean isValidCategoryID(int id) {
        if (!ValidationUtils.isValidID(id) || id >= data.getCategories().size()) {
            System.out.println("Category ID out of range.");
            return false;
        }

        return true;
    }
}
