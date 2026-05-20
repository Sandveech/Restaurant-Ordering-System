package src.main.java.com.restaurant.util;

import src.main.java.com.restaurant.config.AppConstants;
import src.main.java.com.restaurant.config.RestaurantConfig;

public class ValidationUtils {
    private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Returns a value indicating whether the specified id is valid. (Equal to or greater than 0)
     * @param id the id to validate
     * @return {@true} if the id is valid; otherwise, {@false}
     */
    public static boolean isValidID(int id) {
        return id >= 0;
    }

    /**
     * Returns a value indicating whether the specified text is valid. (Not null and not empty)
     * @param text the text to validate
     * @return {@true} if the text is valid; otherwise, {@false}
     */
    public static boolean isValidText(String text) {
        return text != null && !text.isEmpty() && !text.isBlank();
    }

    /**
     * Returns a value indicating whether the specified string contains a symbol.
     * @param str the string to check
     * @return {@true} if the string contains a symbol; otherwise, {@false}
     */
    public static boolean stringContainsSymbols(String str) {
        return str.contains(".*[^a-zA-Z0-9 ].*");
    } 

    /**
     * Returns a value indicating whether the specified string contains at least one number.
     * @param str the string to check
     * @return {@true} if the string contains at leat one number; otherwise {@false}
     */
    public static boolean stringContainsNumbers(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) { return true; }
        }
        return false;
    }

    /**
     * Returns a value indicating whether the specified name is valid. (Within the a length, no symbols, and no numbers)
     * @param name the name to check
     * @return {@true} if the specified name is valid; otherwise, {@false}
     */
    public static boolean isValidName(String name) {
        if (name == null) { return false; }
        int length = name.trim().length();
        return length >= AppConstants.MIN_NAME_LENGTH && length <= AppConstants.MAX_NAME_LENGTH && !stringContainsSymbols(name) && !stringContainsNumbers(name);
    }

    /**
     * Returns a value indicating whether the specified price is valid. (Equal to or greater than the minimum price)
     * @param price the price to check
     * @return {@true} if the specified price is valid; otherwise, {@false}
     */
    public static boolean isValidPrice(double price) {
        return price >= RestaurantConfig.getInstance().getMinPrice();
    }

    /**
     * Returns a value indicating whether the specified email is valid.
     * @param email the email to check
     * @return {@true} if the specified email is valid; otherwise, {@false}
     */
    public static boolean isValidEmail(String email) {
        return isValidText(email);
    }

    /**
     * Returns a value indicating whether the specified phone number is valid.
     * @param phone_number the phone number to check
     * @return {@true} if the specified phone number is valid; otherwise, {@false}
     */
    public static boolean isValidPhoneNumber(String phone_number) {
        return isValidText(phone_number);
    }

    /**
     * Returns a value indicating whether the specified username is valid. (Contains no spaces)
     * @param username the username to check
     * @return {@true} if the specified username is valid; otherwise, {@false}
     */
    public static boolean isValidUsername(String username) {
        return isValidText(username) && !username.contains(" ");
    }

    /**
     * Returns a value indicating whether the specified password is valid. (Equal to or greater than the minimum password length)
     * @param password the password to check
     * @return {@true} if the password is valid; otherwise, {@false}
     */
    public static boolean isValidPassword(String password) {
        return isValidText(password) && password.length() >= MIN_PASSWORD_LENGTH;
    }
}
