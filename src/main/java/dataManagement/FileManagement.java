package dataManagement;

import consoleUI.SafeInput;
import consoleUI.Utils;
import java.io.File;

public class FileManagement {
    /**
     * Input file path from the user, and if all is valid -
     * then create new file with this path, and add it to DataSource
     * @return the file the user wants
     */
    public static File openFileObject(String operationType) {
        File file;

        System.out.println(Utils.takePath + operationType + ":");

        while (true) {
            file = new File(SafeInput.stringInput());

            if (!file.isFile()) {
                System.out.println(Utils.invalidPath);
            }

            else {
                return file;
            }
        }
    }
}
