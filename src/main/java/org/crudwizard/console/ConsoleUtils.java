package org.crudwizard.console;

public class ConsoleUtils {

    private static final String GREEN = "[32m";
    private static final String RED = "[31m";
    private static final String YELLOW = "[33m";
    private static final String WHITE = "[37m";

    public static String green(String message) {
        return (char) 27 + GREEN + message;
    }

    public static String white(String message) {
        return (char) 27 + WHITE + message;
    }

    public static String red(String message) {
        return (char) 27 + RED + message;
    }
}
