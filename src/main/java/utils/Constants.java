package utils;

public class Constants {
    public static final String START_MENU = """
                Welcome to encryptor program!
                Please enter the option number you would like to perform:
                """;


    public static final String INVALID_NUM = "It's not a number. Let's try again:";
    public static final String INVALID_RANGE = "Your choice is out of the range. Let's try again:";
    public static final String INVALID_PATH = "The path you entered is not valid, or not exist! Let's try again:";
    public static final String READING_ERROR = "FileUtils.read(Path) - Something gets wrong with file reading: ";
    public static final String WRITING_ERROR = "FileUtils.write(Path, byte[]) - Something gets wrong with file writing: ";
    public static final String KEYS_WRITING_ERROR = "Something gets wrong with keys writing: ";
    public static final String KEYS_READING_ERROR = "Something gets wrong with keys reading: ";
    public static final String CLASS_NOT_FOUND = "Class of a serialized object cannot be found: ";


    public static final String TAKE_PATH_FOR_ENC = "Please enter the path of the file you want to encrypt:";
    public static final String TAKE_PATH_FOR_DEC = "Please enter the path of the file you want to decrypt:";


    public static final String ENC_FILE_EXTENSION = ".encrypted";
    public static final String DEC_FILE_EXTENSION = "_decrypted.txt";
    public static final String KEY_FILE_NAME = "key.bin";


    public static final String KEY_MESSAGE = "Your key: ";
    public static final String ENC_NAME = "encryption: ";
    public static final String ENC_START = "Encryption process is started.";
    public static final String DEC_START = "Decryption process is started.";
    public static final String END_ENC_MESSAGE = "Encryption process is over.";
    public static final String END_DEC_MESSAGE = "Decryption process is over.";
    public static final String END_PROGRAM = "The program was ended.";
    public static final String ELAPSED_TIME = "Elapsed time: ";
    public static final String MILLISECONDS = " milliseconds";
    public static final String START_READING_KEY = "Algorithm keys writing is started.";
    public static final String END_READING_KEY = "Algorithm keys writing is ended.";
    public static final String START_WRITING_KEY = "Algorithm keys writing is started.";
    public static final String END_WRITING_KEY = "Algorithm keys writing is ended.";
    public static final String GET_KEY_FILE = "Please enter the key file path:";


    public static final String CAESAR_NAME = "CAESAR_CIPHER";
    public static final String CAESAR_ENC = "CIPHER_ENCRYPTION";
    public static final String CAESAR_DEC = "CIPHER_DECRYPTION";
    public static final String XOR_NAME = "XOR_CIPHER";
    public static final String MULTI_NAME = "MULTIPLICATIVE_CIPHER";
    public static final String REVERSE_NAME = "REVERSE_ALGORITHM";
    public static final String DOUBLE_NAME = "DOUBLE_ALGORITHM";


    public static final String ENC_OPT_DESC = "(1) -- Encryption";
    public static final String DEC_OPT_DESC = "(2) -- Decryption";
    public static final String CLOSE_OPT_DESC = "(3) -- Close program";


    public static final int ENC_OPT = 1;
    public static final int DEC_OPT = 2;
    public static final int CLOSE_OPT = 3;
    public static final byte CHARACTERS_RANGE = 127;
    public static final int XOR_KEY_INDEX = 0;
    public static final int CAESAR_KEY_INDEX = 1;
    public static final int MULTI_KEY_INDEX = 2;


    public static final boolean EXIT_PROGRAM = true;
    public static final boolean NOT_EXIT = false;
    public static final boolean ENC_FLAG = true;
    public static final boolean DEC_FLAG = false;
}
