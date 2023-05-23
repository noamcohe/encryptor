package dataManagement;
import consoleUI.Input;
import consoleUI.HelpConstants;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class CryptoUtils {

    /**
     * get a new file path with the given extension.
     * @param oldPath The exist file path.
     * @param newExtension the extension needs to be after the new file name.
     * @return New path contains old file name, but with new extension that given as a param.
     */
    public Path getNewFilePath(@NotNull Path oldPath, String newExtension) {
        String originalFilePath = oldPath.toString();
        String fileNameWithoutExtension = originalFilePath.substring(0, originalFilePath.lastIndexOf('.'));

        return Path.of((fileNameWithoutExtension + newExtension));
    }


    /**
     * Encrypt or decrypt a given file, depending on 'encryptFlag'.
     * @param fileToProcess The file needs to be processed (encrypted or decrypted).
     * @param encryptFlag mention if the given file needs to be encrypted or decrypted.
     */
    public void processFile(Path fileToProcess, boolean encryptFlag) {
        CaesarEncryptor caesarOptions;
        int key;

        if (encryptFlag) {
            caesarOptions = new CaesarEncryptor();

            key = caesarOptions.getKey();
            System.out.println(key);
        }

        else {
            key = Input.inputKey();
            caesarOptions = new CaesarEncryptor(key);
        }

        String dataToProcess = FileUtils.readFileToStr(fileToProcess);
        String processedData = caesarOptions.processString(dataToProcess, key, encryptFlag);

        Path newPath = encryptFlag ? getNewFilePath(fileToProcess, HelpConstants.ENCRYPTED_FILE_EXTENSION) :
                                     getNewFilePath(fileToProcess, HelpConstants.DECRYPTED_FILE_EXTENSION);
        FileUtils.writeStrToFile(newPath, processedData);
    }
}
