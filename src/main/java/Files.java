import java.io.File;

public class Files {

    public static File createFile(String path) {
        File textFile = new File(path);
        if (!Validation.isValidExtension(textFile) &&
                !(textFile.exists() && !textFile.isDirectory())) {
            System.out.println("The path you entered is not valid, or not exist! Let's try again:");
            return null;
        }

        return textFile;
    }
}
