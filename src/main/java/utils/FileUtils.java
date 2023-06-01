package utils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileUtils {

    public String read(Path filePath) {
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(Constants.READING_ERROR);
            return "";
        }
    }


    public boolean write(Path filePath, byte[] newData) {
        try {
            Files.createFile(filePath);
            Files.write(filePath, newData, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    /**
     * get a new file path with the given extension.
     * @param oldPath The exist file path.
     * @param newExtension the extension needs to be after the new file name.
     * @return New path contains old file name, but with new extension that given as a param.
     */
    public Path getNewPath(String oldPath, String newExtension) {
        String fileBaseName = oldPath.substring(0, oldPath.lastIndexOf('.'));

        return Path.of((fileBaseName + newExtension));
    }
}
