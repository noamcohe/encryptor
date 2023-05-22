package consoleUI;

import java.util.regex.Pattern;

public class Utils {
    public static String startMenuMessage = """
                Welcome to encryptor program!
                Please enter the option number you would like to perform:
                """;
    public static String invalidChoiceInput =
            "Your number is out of the range, or it's not a number. Let's try again:";
    public static String invalidPath = "The path you entered is not valid, or not exist! Let's try again:";
    public static String takePath = "Please enter the path of the file you want to ";

    public static Pattern createRangePattern(int range) {
        return Pattern.compile(("[1-" + range + "]"));
    }

}
