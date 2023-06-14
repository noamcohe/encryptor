package utils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import static utils.Constants.*;

public class FileUtils {

    public byte[] read(Path filePath) {
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(filePath);
        } catch (IOException e) {
            programLogger.error(READING_ERROR + e);
        }

        return data;
    }


    public void write(Path filePath, byte[] dstFileData) {
        try {
            Files.write(filePath, dstFileData, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (IOException | UnsupportedOperationException e) {
            programLogger.error(WRITING_ERROR + e);
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
