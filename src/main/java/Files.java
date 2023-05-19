import java.io.File;

public class Files {

    /**
     * Get a path of file the user want to encrypt / decrypt.
     * if everything is O.K, then send back the file object.
     * @param path the path of the file to encrypt / decrypt.
     * @return the file object.
     */
    public static File createFile(String path) {
        File textFile = new File(path);
        // Check the validation, existence and extension of the file:
        if (!Validation.isValidExtension(textFile) &&
                !(textFile.exists() && !textFile.isDirectory())) {
            System.out.println("The path you entered is not valid, or not exist! Let's try again:");
            return null;
        }

        return textFile;
    }
}
