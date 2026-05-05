package src.main.java.com.restaurant.model;

import java.util.ArrayList;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class MenuItem {
    // fields
    private static int count = 0;
    private int id;
    private String name;
    private String description;
    private Category category;
    private Boolean active;
    private ArrayList<ItemPriceOption> price_options = new ArrayList<ItemPriceOption>();

    // constructor
    public MenuItem(String name, String description, Category category, Boolean active) {
        setID(count++);
        setName(name);
        setDescription(description);
        setCategory(category);
        setActive(active);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Description: %s, Category: %s, Active: %b", name, description, getCategory().getName(), active);
    }

    // getters and setters
    /**
     * Returns the historical count of menu items.
     * @return the historical count of menu items
     */
    private int getCount() { return count; }

    /**
     * Returns the id of this menu item.
     * @return the id of this menu item
     */
    public int getID() { return id; }

    /**
     * Returns the name of this menu item.
     * @return the name of this menu item
     */
    public String getName() { return name; }

    /**
     * Returns the description of this menu item.
     * @return the description of this menu item
     */
    public String getDescription() { return description; }

    /**
     * Returns the category of this menu item.
     * @return the category of this menu item
     */
    public Category getCategory() { return category; }

    /**
     * Returns {@true} if this menu item is active; otherwise, {@false}
     * @return {@true} if this menu item is active; otherwise, {@false}
     */
    public Boolean isActive() { return active; }

    /**
     * Returns the list of item price options.
     * @return the list of item price options
     */
    public ArrayList<ItemPriceOption> getItemPriceOption() { return this.price_options; }

    /**
     * Sets the id of this menu item.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the name of this menu item.
     * @param name the name to set to
     */
    public void setName(String name) {
        this.name = (ValidationUtils.isValidName(name)) ? name : "Unnamed Item";
    }


    /**
     * Sets the description of this menu item.
     * @param description the description to set to
     */
    public void setDescription(String description) {
        this.description = (ValidationUtils.isValidText(description)) ? description : "No description.";
    }

    /**
     * Sets the category of this menu item.
     * @param category the category to set to
     */
    public void setCategory(Category category) {
        if (category != null) { this.category = category; }
    }

    /**
     * Sets the active state of this menu item.
     * @param active {@true} for this menu item to be active; otherwise, {@false}
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Returns the index of the price option via label.
     * @param label the label to search for
     * @return the index of the price option
     */
    public int indexOfPriceOption(String label) {
        for (int i = 0; i < price_options.size(); i++) {
            ItemPriceOption po = price_options.get(i);
            if (po == null) { continue; }
            if (po.getLabel() == label) { return i; }
        }

        return -1;
    }

    /**
     * Creates a new price option for this menu item. Returns {@true} if the price option was successfully created; otherwise, {@false}.
     * @param label the label of the price option
     * @param price the price of the price option 
     * @return {@true} if the price option was successfully created; otherwise, {@false}
     */
    public Boolean createPriceOption(String label, double price) {
        if (!ValidationUtils.isValidText(label)) { return false; }
        
        Boolean optionExists = indexOfPriceOption(label) != -1;
        if (!optionExists) {
            price_options.add(new ItemPriceOption(label, price));
            return true;
        }

        return false;
    }
}
