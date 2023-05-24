package consoleUI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import enums.Choice;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;
import utils.Constants;

public class ConsoleInput implements GeneralInput {

    public static Scanner scanner = new Scanner(System.in);


    /**
     * Input a number in a safe way. take it like a string and then convert it.
     * @return The number the user entered as an integer type.
     */
    public int getInt() {
        String selection = scanner.nextLine();

        while (!NumberUtils.isParsable(selection)) {
            System.out.println(Constants.INVALID_NUM_INPUT);
            selection = scanner.nextLine();
        }

        return Integer.parseInt(selection);
    }


    /**
     * Input an exist file path, and returns it if it's proper.
     * @return The path the user entered.
     */
    public @NotNull Path getPath() {
        Path path;

        System.out.println(Constants.TAKE_PATH);

        while (true) {
            path = Paths.get(scanner.nextLine());

            if (!Files.isRegularFile(path)) {
                System.out.println(Constants.INVALID_PATH);
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
    public int getKey() {
        System.out.println(Constants.TAKE_KEY);
        return getInt();
    }


    /**
     * Input a choice enum in a safe way. take it like an int,
     * After that make sure if it's in 'Choice' enum range -
     * And then convert it.
     * @return The user choice as a 'Choice' enum type.
     */
    public Choice getChoice() {
        int userChoice = getInt();

        while (!Choice.inRange(userChoice)) {
            System.out.println(Constants.INVALID_RANGE);
            userChoice = getInt();
        }

        return Choice.values()[userChoice - 1];
    }
}
