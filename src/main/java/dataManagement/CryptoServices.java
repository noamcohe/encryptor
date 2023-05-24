package dataManagement;
import consoleUI.ConsoleInput;
import utils.Constants;
import java.nio.file.Path;

import consoleUI.GeneralInput;

public class CryptoServices {
    /**
     * Doing all the general operations for encryption / decryption.
     * Get an encryption flag that tell's method if encryption is needed or decryption.
     * @param fileToEncOrDec The file we want to encrypt / decrypt (depending on the 'encryptionFlag')
     * @param key The key uses by Caesar Cipher algorithm to encrypt / decrypt.
     * @param encryptionFlag A flag mention if we want to implement file encryption or file decryption.
     */
    private void baseCryptoOperations(Path fileToEncOrDec, int key, boolean encryptionFlag) {
        FileServices fileServices = new FileServices();
        CaesarCipher caesarCipher = new CaesarCipher();

        String dataToEncOrDec = fileServices.fileReading(fileToEncOrDec);
        String encOrDecData = encryptionFlag ?
                caesarCipher.stringEncryption(dataToEncOrDec, key) :
                caesarCipher.stringDecryption(dataToEncOrDec, key);

        Path newPath = encryptionFlag ?
                fileServices.getNewFilePath(fileToEncOrDec, Constants.ENCRYPTED_FILE_EXTENSION) :
                fileServices.getNewFilePath(fileToEncOrDec, Constants.DECRYPTED_FILE_EXTENSION);
        fileServices.fileWriting(newPath, encOrDecData);
    }


    /**
     * Encrypt a given file.
     * @param fileForEncryption The file needs to be encrypted.
     */
    public void fileEncryption(Path fileForEncryption) {
        CaesarCipher caesarCipher = new CaesarCipher();

        int key = caesarCipher.getKey();
        System.out.println(key);

        baseCryptoOperations(fileForEncryption, key, Constants.ENCRYPTION_FLAG);
    }


    /**
     * Decrypt a given file.
     * @param fileForDecryption The file needs to be decrypted.
     */
    public void fileDecryption(Path fileForDecryption) {
        GeneralInput consoleInput = new ConsoleInput();
        int key = consoleInput.getKey();

        baseCryptoOperations(fileForDecryption, key, Constants.DECRYPTION_FLAG);
    }
}
