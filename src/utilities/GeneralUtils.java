package src.Utilities;

public class GeneralUtils {
    public static Boolean isValidTextInput(String text) {
        return text != null && !text.isBlank() && !text.isEmpty();
    }
}
