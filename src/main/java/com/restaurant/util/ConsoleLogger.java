package src.main.java.com.restaurant.util;

public class ConsoleLogger {
    private ConsoleLogger() {};

    public static void printSuccess(String message) {
        System.out.println(AnsiColor.GREEN + "[Success] " + message + AnsiColor.RESET);
    }

    public static void printWarning(String message) {
        System.out.println(AnsiColor.YELLOW + "[Warning] " + message + AnsiColor.RESET);
    }

    public static void printInfo(String message) {
        System.out.println(AnsiColor.BLUE + "[Info] " + message + AnsiColor.RESET);
    }

    public static void printError(String message) {
        System.out.println(AnsiColor.RED + "[Error] " + message + AnsiColor.RESET);
    }
}
