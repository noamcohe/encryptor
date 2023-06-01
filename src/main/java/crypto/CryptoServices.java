package crypto;
import crypto.cipherAlgorithms.Cipher;
import utils.Constants;
import utils.FileUtils;

public class CryptoServices {

    /**
     * Doing all the general operations for encryption / decryption.
     * Get an encryption flag that tell's method if encryption is needed or decryption.
     */
    private String fileEncDec(Cipher cipher) {
        FileUtils fileUtils = new FileUtils();

        String srcFileData = fileUtils.read(cipher.sourceFilePath());

        String destFileData = cipher.encryptionFlag() ?
                cipher.encrypt(srcFileData) :
                cipher.decrypt(srcFileData);

        cipher.isSucceeded(fileUtils.write(cipher.destFilePath(), destFileData.getBytes()));
        return cipher.toString();
    }


    public String fileEncryption(Cipher cipher) {
        return fileEncDec(cipher);
    }

    public String  fileDecryption(Cipher cipher) {
        return fileEncDec(cipher);
    }
}
