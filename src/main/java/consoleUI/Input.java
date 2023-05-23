package consoleUI;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;

public class Input {

    public static Scanner scanner = new Scanner(System.in);

    public static int numInput() {
        String selection = scanner.nextLine();

        while (!NumberUtils.isParsable(selection)) {
            System.out.println(Utils.INVALID_NUM_INPUT);
            selection = scanner.nextLine();
        }

        return Integer.parseInt(selection);
    }

    public static Path pathInput() {
        Path path;

        System.out.println(Utils.TAKE_PATH);

        while (true) {
            path = Paths.get(scanner.nextLine());

            if (!Files.isRegularFile(path)) {
                System.out.println(Utils.INVALID_PATH);
            }

            else {
                return path;
            }
        }

    }

}
