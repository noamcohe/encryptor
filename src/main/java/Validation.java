import java.util.regex.Pattern;
import java.io.File;

public class Validation {

    /**
     * Take a string that intended to be an integer, and make sure that input is legal.
     * @param choice the user input, as a string variable.
     * @return true if it's a number, false otherwise.
     */
    public static boolean isNumeric(String choice) {
        if (choice == null) {
            return false;
        }

        // regular expression - check that user choice is a number:
        Pattern pattern = Pattern.compile("\\d+");

        return pattern.matcher(choice).matches();
    }

    /**
     * get a file, and make sure the file extension is '.txt'.
     * @param file A new file that the user sent.
     * @return if the extension file is valid.
     */
    public static boolean isValidExtension(File file) {
        if (file == null) {
            return false;
        }

        String fileName = file.getName();

        // Go to the last instance of '.' in the file name:
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            // And after that, take all the characters from the '.' until the end.
            String extension = fileName.substring(index + 1);
            return extension.equals("txt");
        }

        return false;
    }
}
