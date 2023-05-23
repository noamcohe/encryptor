package consoleUI;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;

public class Input {

    public static Scanner scanner = new Scanner(System.in);


    /**
     * Input a number in a safe way. take it like a string and then convert it.
     * @return The number the user entered as an integer type.
     */
    public static int integerInput() {
        String selection = scanner.nextLine();

        while (!NumberUtils.isParsable(selection)) {
            System.out.println(HelpConstants.INVALID_NUM_INPUT);
            selection = scanner.nextLine();
        }

        return Integer.parseInt(selection);
    }


    /**
     * Input an exist file path, and returns it if it's proper.
     * @return The path the user entered.
     */
    public static Path pathInput() {
        Path path;

        System.out.println(HelpConstants.TAKE_PATH);

        while (true) {
            path = Paths.get(scanner.nextLine());

            if (!Files.isRegularFile(path)) {
                System.out.println(HelpConstants.INVALID_PATH);
            }

            else {
                return path;
            }
        }

    }


    /**
     * Input a key needed for decryption a file.
     * @return The key the user entered.
     */
    public static int inputKey() {
        System.out.println(HelpConstants.TAKE_KEY);
        return integerInput();
    }

}
