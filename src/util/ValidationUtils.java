package src.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.config.AppConstants;

public class ValidationUtils {
    // ID validation
    public static Boolean isValidID(int id) {
        return id >= 0;
    }

    // text validation
    public static Boolean isValidText(String text) {
        return text != null && !text.isEmpty() && !text.isBlank();
    }

    public static Boolean stringContainsSymbols(String str) {
        return str.contains(".*[^a-zA-Z0-9 ].*");
    } 

    // name validation
    public static Boolean isValidName(String name) {
        if (name == null) { return false; }
        int length = name.trim().length();
        return length >= AppConstants.MIN_NAME_LENGTH && length <= AppConstants.MAX_NAME_LENGTH && !stringContainsSymbols(name);
    }

    // price validation
    public static Boolean isValidPrice(double price) {
        return price >= AppConstants.MIN_PRICE;
    }

    // email validation
    public static Boolean isValidEmail(String email) {
        return isValidText(email);
    }

    // phone number validation
    public static Boolean isValidPhoneNumber(String phone_number) {
        return isValidText(phone_number);
    }
}
