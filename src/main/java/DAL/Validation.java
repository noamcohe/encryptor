package DAL;

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
}
