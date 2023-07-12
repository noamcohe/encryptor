package userInput;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ValueRange;
import java.util.Scanner;
import com.google.inject.Singleton;
import org.apache.commons.lang3.math.NumberUtils;
import utils.programLogger;

@Singleton
public class ConsoleInput implements GeneralInput {
    final String NOT_PARSABLE = "It's not a number. Let's try again:";
    final String INVALID_RANGE = "Your number is out of range. Let's try again:";
    final String INVALID_PATH = "The path you entered is not valid, or not exist! Let's try again:";
    final String INVALID_FILE_PATH = "The file path you entered is not exist or it's not a file. Let's try again:";

    public Scanner scanner = new Scanner(System.in);


    @Override
    public int getInt() {
        String input = scanner.nextLine();

        while (!NumberUtils.isParsable(input)) {
            programLogger.display(NOT_PARSABLE);
            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }


    @Override
    public Path getPath(String message, boolean requiresFile) {
        Path path;

        System.out.println(message);

        while (true) {
            path = Paths.get(scanner.nextLine());

            if (requiresFile) {
                if (!Files.isRegularFile(path)) {
                    programLogger.display(INVALID_FILE_PATH);
                } else if (Files.exists(path)) {
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
        int num = getInt();

        while (!ValueRange.of(1, range).isValidIntValue(num)) {
            programLogger.display(INVALID_RANGE);
            num = getInt();
        }

        return num;
    }


    @Override
    public void close() {
        scanner.close();
    }
}
