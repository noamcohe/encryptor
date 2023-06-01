package utils;

public class Constants {
    public static final String START_MENU = """
                Welcome to encryptor program!
                Please enter the option number you would like to perform:
                """;
    public static final String ENCRYPTION_MENU = """
            You chose encryption.
            Please enter the option number of the encryption algorithm you would like to use:
            """;
    public static final String DECRYPTION_MENU = """
            You chose decryption.
            Please enter the option number of the decryption algorithm you would like to use:
            """;
    public static final String INVALID_NUM = "It's not a number. Let's try again:";
    public static final String INVALID_RANGE = "Your choice is out of the range. Let's try again:";
    public static final String INVALID_PATH = "The path you entered is not valid, or not exist! Let's try again:";
    public static final String TAKE_PATH_FOR_ENCRYPTION = "Please enter the path of the file you want to encrypt:";
    public static final String TAKE_PATH_FOR_DECRYPTION = "Please enter the path of the file you want to decrypt:";
    public static final String KEY_MESSAGE = "Your key: ";
    public static final String INPUT_FILE = "input file: ";
    public static final String OUTPUT_FILE = "output file: ";
    public static final String TAKE_KEY = "Enter the key you need:";
    public static final String READING_ERROR = "FileUtils.read(Path) - Something gets wrong with file reading ";
    public static final String WRITING_ERROR = "FileUtils.write(Path, byte[]) - Something gets wrong with file writing ";
    public static final String ENCRYPTED_FILE = ".encrypted";
    public static final String DECRYPTED_FILE = "_decrypted.txt";
    public static final int CHARACTERS_RANGE = 128;
    public static final boolean EXIT_PROGRAM = true;
    public static final boolean ENC_FLAG = true;
    public static final boolean DEC_FLAG = false;
    public static final String DEC_KEY = "decryption key: ";
    public static final String ENC_NAME = "encryption: ";
    public static final String START_ENC = "Encryption process started!";
    public static final String START_DEC = "Decryption process started!";
    public static final String END_ENC = "Encryption process is over.";
    public static final String END_DEC = "Decryption process is over.";
    public static final String CHOOSE_INNER_CIPHER = "Please choose the cipher you want to reverse:";
    public static final String CAESAR_NAME = "CAESAR_CIPHER";
    public static final String XOR_NAME = "XOR_CIPHER";
    public static final String MULTIPLICATION_NAME = "MULTIPLICATION_CIPHER";
    public static final String REVERSE_NAME = "REVERSE_ALGORITHM";
    public static final String CAESAR_DESC = "(1) -- Caesar Cipher";
    public static final String XOR_DESC = "(2) -- XOR Cipher";
    public static final String MULTIPLICATION_DESC = "(3) -- Multiplication Cipher";
    public static final String REVERSE_DESC = "(4) -- Reverse Algorithm";
    public static final String IS_SUCCEEDED = "Succeeded: ";

}
