package ConsoleUI;
import DAL.FileObject;
import Enums.Choice;
import DalObject.DalObject;

public class Main {

    static DalObject dalObject = new DalObject();

    /**
     * Input file path from the user, and if all is valid -
     * then create new file with this path, and add it to DataSource
     * @return the file the user wants
     */
    public static FileObject addNewFile() {
        FileObject file;

        while (true) {
            file = new FileObject(SafeInput.inputFilePath());

            if (file.canRead() && file.exists() && file.isFile()) {
                dalObject.createFile(file);
                return file;
            }

            System.out.println("The path you entered is not valid, or not exist! Let's try again:");
        }
    }

    public static void main(String[] args) {
        Choice startSelection;
        boolean flag = true;

        while (flag) {

            startSelection = Menu.startMenu();

            switch (startSelection) {
                case ENCRYPTION -> {
                    dalObject.createEncryptedFile(addNewFile().encryptFile());
                }
                case DECRYPTION -> {
                    dalObject.createDecryptedFile(addNewFile().decryptFile());
                }
                case CLOSE_PROGRAM -> flag = false;
            }
        }
    }

}

