package ConsoleUI;

import java.util.Scanner;

public class SafeInput {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * This function take a string input from the user, and convert it to integer.
     * We don't use 'nextInt()' method, to avoid exceptions like illegal type.
     * @return The user input as an integer variable.
     */
    public static int integerInput() {
        String selection = scanner.nextLine();

        // While it's not a number:
        while (!selection.matches("[1-3]")) {
            System.out.println("Your number is out of the range, or it's not a number. Let's try again:");
            selection = scanner.nextLine();
        }

        return Integer.parseInt(selection);
    }

    public static String stringInput() {
        return scanner.nextLine();
    }

}
