package ConsoleUI;
import Enums.Choice;
import java.io.File;
import DalObject.DalObject;

public class Main {

    static DalObject dalObject = new DalObject();

    public static File addNewFile() {
        File file;

        while (true) {
            file = new File(SafeInput.inputFilePath());

            if (file.canRead() && file.exists() && file.isFile()) {
                dalObject.createFile(file);
                return file;
            }

            System.out.println("The path you entered is not valid, or not exist! Let's try again:");
        }
    }

    public static void encryptNewFile(File file) {
    }

    public static void decryptNewFile(File file) {

    }

    public static void main(String[] args) {
        Choice startSelection;
        boolean flag = true;

        while (flag) {

            startSelection = Menu.startMenu();

            switch (startSelection) {
                case ENCRYPTION -> {
                    File fileToEncrypt = addNewFile();
                    encryptNewFile(fileToEncrypt);
                }
                case DECRYPTION -> {
                    File fileToDecrypt = addNewFile();
                    decryptNewFile(fileToDecrypt);
                }
                case CLOSE_PROGRAM -> flag = false;
            }
        }
    }

}

