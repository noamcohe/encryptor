package DataManagement;

import ConsoleUI.SafeInput;
import java.io.File;

public class FileManagement {
    /**
     * Input file path from the user, and if all is valid -
     * then create new file with this path, and add it to DataSource
     * @return the file the user wants
     */
    public static File openFileObject(String operationType) {
        File file;

        System.out.println("Please enter the path of the file you want to " + operationType + ":");

        while (true) {
            file = new File(SafeInput.stringInput());

            if (!file.isFile()) {
                System.out.println("The path you entered is not valid, or not exist! Let's try again:");
            }

            else {
                return file;
            }
        }
    }
}
