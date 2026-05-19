package src.main.java.com.restaurant.model;

import java.util.ArrayList;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.interfaces.Activatable;
import src.main.java.com.restaurant.interfaces.Displayable;
import src.main.java.com.restaurant.util.ValidationUtils;

public class MenuItem implements Displayable, Activatable {
    // fields
    private static int menu_item_count = 0;
    private int id;
    private String name;
    private String description;
    private Category category;
    private boolean active;
    private ArrayList<ItemPriceOption> price_options = new ArrayList<ItemPriceOption>();

    // constructor
    public MenuItem(String name, String description, Category category, Boolean active) {
        setID(menu_item_count++);
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
    private static int getMenuItemCount() { return menu_item_count; }

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
    public boolean isActive() { return active; }

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
    private int indexOfPriceOption(String label) {
        for (int i = 0; i < price_options.size(); i++) {
            ItemPriceOption po = price_options.get(i);
            if (po == null) { continue; }
            if (po.getLabel() == label) { return i; }
        }

        return -1;
    }

    /**
     * Returns {@true} if the specified size price option exists; otherwise, {@false}.
     * @param label the size label to check for
     * @return {@true} if the specified size price option exists; otherwise, {@false}
     */
    public Boolean priceOptionExists(String label) {
        return indexOfPriceOption(label) != -1;
    }

    /**
     * Returns the price of this menu item with the specified size label.
     * @param label the size label to search for
     * @return the price of this menu item with the specified size label
     */
    public double priceOfSize(String label) {
        int price_index = indexOfPriceOption(label);
        if (price_index == -1) { return 0; }

        return price_options.get(price_index).getPrice();
    }
    
    /**
     * Creates a new price option for this menu item. Returns {@true} if the price option was successfully created; otherwise, {@false}.
     * @param label the label of the price option
     * @param price the price of the price option 
     * @return {@true} if the price option was successfully created; otherwise, {@false}
     */
    public Boolean createPriceOption(String label, double price) {
        if (!ValidationUtils.isValidText(label)) { return false; }
        
        if (!priceOptionExists(label)) {
            price_options.add(new ItemPriceOption(label, price));
            return true;
        }

        return false;
    }
    
    /**
     * Displays this menu item.
     */
    @Override
    public void display() {
        String sizes = "(";
        int len = price_options.size();
        for (int i = 0; i < len; i++) {
            ItemPriceOption op = price_options.get(i);
            sizes += op.getLabel() + ": " + op.getPrice();

            if (i != len - 1) { sizes += ", "; }
        }
        for (ItemPriceOption op : price_options) {
            if (op == null) { continue; }
            
        }
        sizes += ")";

        System.out.println(String.format("Name: %s, Sizes: %s", name, sizes));
    }

    /**
     * Activates this menu item.
     */
    public void activate() {
        this.active = true;
    }

    /**
     * Deactivates this menu item.
     */
    public void deactivate() {
        this.active = false;
    }
}
