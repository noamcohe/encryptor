import java.util.regex.Pattern;
import java.io.File;

public class Validation {

    public static boolean isNumeric(String choice) {
        if (choice == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("\\d+");

        return pattern.matcher(choice).matches();
    }

    public static boolean isValidExtension(File file) {
        if (file == null) {
            return false;
        }

        String fileName = file.getName();

        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            String extension = fileName.substring(index + 1);
            return extension.equals("txt");
        }

        return false;
    }
}
