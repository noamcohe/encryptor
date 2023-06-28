package userInput;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ValueRange;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;
import utils.programLogger;

public class ConsoleInput implements GeneralInput {
    final String INVALID_NUM = "It's not a number. Let's try again:";
    final String INVALID_RANGE = "Your choice is out of the range. Let's try again:";
    final String INVALID_PATH = "The path you entered is not valid, or not exist! Let's try again:";
    final String INVALID_KEY_PATH = "The keys file path you entered is not valid, or not exist! Let's try again:";

    public static Scanner scanner = new Scanner(System.in);


    @Override
    public int getInt() {
        String selection = scanner.nextLine();

        while (!NumberUtils.isParsable(selection)) {
            programLogger.display(INVALID_NUM);
            selection = scanner.nextLine();
        }

        return Integer.parseInt(selection);
    }


    @Override
    public Path getPath(String message, boolean isKeyFile) {
        Path path;

        System.out.println(message);

        while (true) {
            path = Paths.get(scanner.nextLine());

            if (isKeyFile) {
                if (!Files.isRegularFile(path)) {
                    programLogger.display(INVALID_KEY_PATH);
                } else {
                    return path;
                }
            } else if (!Files.isRegularFile(path) && !Files.isDirectory(path)) {
                programLogger.display(INVALID_PATH);
            } else {
                return path;
            }
        }
    }


    @Override
    public int getNumByRange(int range) {
        int userNum = getInt();

        while (!ValueRange.of(1, range).isValidIntValue(userNum)) {
            programLogger.display(INVALID_RANGE);
            userNum = getInt();
        }

        return userNum;
    }
}
