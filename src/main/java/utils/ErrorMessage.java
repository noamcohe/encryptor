package utils;

public class ErrorMessage {
    private static final StringBuilder data = new StringBuilder();

    public static void data(String message) {
        data.append(message).append("\n");
    }

    public static void display() {
        System.out.println(data);
    }

    public static void display(String message) {
        System.out.println(message);
    }
}
