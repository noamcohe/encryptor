package consoleUI;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SafeInput {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * This function take a string input from the user, and convert it to integer.
     * We don't use 'nextInt()' method, to avoid exceptions like illegal type.
     * @return The user input as an integer variable.
     */
    public static int integerInput(Pattern range) {
        String selection = scanner.nextLine();

        while (!range.matcher(selection).matches()) {
            System.out.println(Utils.invalidChoiceInput);
            selection = scanner.nextLine();
        }

        return Integer.parseInt(selection);
    }

    public static String stringInput() {
        return scanner.nextLine();
    }

}
