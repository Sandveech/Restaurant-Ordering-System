package src.main.java.com.restaurant.model;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;
import src.main.java.com.restaurant.util.ValidationUtils;

public class ItemPriceOption {
    // fields
    private static int item_price_option_count = 0;
    private int id;
    private String label;
    private double price;
    private Boolean active;

    // constructor
    public ItemPriceOption(String label, double price) {
        setID(item_price_option_count++);
        setLabel(label);
        setPrice(price);
    }

    // getters and setters
    /**
     * Returns the historical count of item price option.
     * @return the historical count of item price option
     */
    private static int getItemPriceOptionCount() { return item_price_option_count; }

    /**
     * Returns the label of this item price option.
     * @return the label of this item price option
     */
    public String getLabel() { return label; }

    /**
     * Returns the price of this item price option.
     * @return the item price option
     */
    public double getPrice() { return price; }

    /**
     * Returns {@true} if this price option is active; otherwise, {@false}.
     * @return {@true} if this price option is active; otherwise, {@false}.
     */
    public Boolean isActive() { return active; }

    /**
     * Sets the id of this item price option.
     * @param id the id to set to
     */
    private void setID(int id) {
        this.id = (ValidationUtils.isValidID(id)) ? id : AppConstants.INVALID_ID;
    }

    /**
     * Sets the label of this item price option
     * @param label the label to set to
     */
    public void setLabel(String label) {
        this.label = (ValidationUtils.isValidText(label)) ? label : "Unknown Size";
    }

    /**
     * Sets the price of this item price option
     * @param price the price to set to
     */
    public void setPrice(double price) {
        this.price = (price < 0) ? RestaurantConfig.getInstance().getMinPrice() : price;
    }

    /**
     * Sets the active state of this item price option.
     * @param active {@true} if this price option is to be active; otherwise, {@false}
     */
    public void setActive(Boolean active) {
        this.active = active;
    }
}
