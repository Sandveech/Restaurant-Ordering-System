package src.main.java.com.restaurant.interfaces;

import src.main.java.com.restaurant.model.MenuItem;

@FunctionalInterface
public interface ItemApprover {
    void approve(MenuItem item);
}