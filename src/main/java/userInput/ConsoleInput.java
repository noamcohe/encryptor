package userInput;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ValueRange;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;
import utils.programLogger;
import static utils.Constants.*;

public class ConsoleInput implements GeneralInput {

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


    /**
     * Input an exist file path, and returns it if it's proper.
     *
     * @return The path the user entered.
     */
    @Override
    public Path getPath(String message) {
        Path path;

        System.out.println(message);

        while (true) {
            path = Paths.get(scanner.nextLine());

            if (!Files.isRegularFile(path)) {
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
