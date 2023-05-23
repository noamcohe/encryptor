package consoleUI;

public class HelpConstants {
    public static final String START_MENU_MESSAGE = """
                Welcome to encryptor program!
                Please enter the option number you would like to perform:
                """;
    public static final String INVALID_NUM_INPUT = "It's not a number. Let's try again:";
    public static final String INVALID_RANGE = "Your choice is out of the range. Let's try again:";
    public static final String INVALID_PATH = "The path you entered is not valid, or not exist! Let's try again:";
    public static final String TAKE_PATH = "Please enter the path of the file you want to encrypt / decrypt:";
    public static final String TAKE_KEY = "Enter the key you need to decrypt your file:";
    public static final String READING_ERROR = "Something gets wrong with file reading";
    public static final String WRITING_ERROR = "Something gets wrong with file writing";
    public static final String ENCRYPTED_FILE_EXTENSION = ".encrypted";
    public static final String DECRYPTED_FILE_EXTENSION = "_decrypted.txt";
    public static final boolean ENCRYPTION_FLAG = true;
    public static final boolean DECRYPTION_FLAG = false;
}
