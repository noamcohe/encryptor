package ConsoleUI;

import DAL.Validation;

import java.util.Scanner;

public class SafeInput {

    static Scanner scanner = new Scanner(System.in);

    /**
     * This function take a string input from the user, and convert it to integer.
     * We don't use 'nextInt()' method, to avoid exceptions like illegal type.
     * @return The user input as an integer variable.
     */
    public static int integerInput() {
        String selection = scanner.next();

        while (!Validation.isNumeric(selection)) {
            System.out.println("""
                        You must enter a number!
                        Let's try again:""");
            selection = scanner.next();
        }

        return Integer.parseInt(selection);
    }

    /**
     * Input the path of a file from the user.
     * @return The user input.
     */
    public static String inputFilePath() {
        String filePath;

        System.out.println("Please enter the path of the file you want to encrypt / decrypt:");
        filePath = scanner.next();

        return filePath;
    }

}
