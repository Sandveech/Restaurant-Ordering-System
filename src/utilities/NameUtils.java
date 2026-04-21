package src.Utilities;

public class NameUtils {
    public static final int MIN_NAME_CHAR = 1;
    public static final int MAX_NAME_CHAR = 35;  
    public static final String DEFAULT_NAME = "Unknown";

    public static Boolean isValidName(String name) {
        return GeneralUtils.isValidTextInput(name) && name.length() >= MIN_NAME_CHAR && name.length() <= MAX_NAME_CHAR;
    }
}
