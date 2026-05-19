package src.main.java.com.restaurant.util;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;

public class ValidationUtils {
    private static final int MIN_PASSWORD_LENGTH = 8;

    // ID validation
    public static boolean isValidID(int id) {
        return id >= 0;
    }

    // text validation
    public static boolean isValidText(String text) {
        return text != null && !text.isEmpty() && !text.isBlank();
    }

    public static boolean stringContainsSymbols(String str) {
        return str.contains(".*[^a-zA-Z0-9 ].*");
    } 

    // name validation
    public static boolean isValidName(String name) {
        if (name == null) { return false; }
        int length = name.trim().length();
        return length >= AppConstants.MIN_NAME_LENGTH && length <= AppConstants.MAX_NAME_LENGTH && !stringContainsSymbols(name);
    }

    // price validation
    public static boolean isValidPrice(double price) {
        return price >= RestaurantConfig.getInstance().getMinPrice();
    }

    // email validation
    public static boolean isValidEmail(String email) {
        return isValidText(email);
    }

    // phone number validation
    public static boolean isValidPhoneNumber(String phone_number) {
        return isValidText(phone_number);
    }

    // username validation
    public static boolean isValidUsername(String username) {
        return isValidText(username) && !username.contains(" ");
    }

    // password validation
    public static boolean isValidPassword(String password) {
        return isValidText(password) && password.length() >= MIN_PASSWORD_LENGTH;
    }
}
