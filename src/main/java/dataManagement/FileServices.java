package dataManagement;
import utils.Constants;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileServices {

    /**
     * Read all the given file data into a string.
     * @param filePath path of the file to read.
     * @return String contains all the file data.
     */
    public @NotNull String fileReading(Path filePath) {
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(Constants.READING_ERROR);
            return "";
        }
    }


    /**
     * create an new output file, and write into this file the given string.
     * @param filePath Path for the new file.
     * @param newData string contains all the data needs to written to the output file.
     */
    public void fileWriting(Path filePath, @NotNull String newData) {
        try {
            Files.createFile(filePath);
            Files.write(filePath, newData.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println(Constants.WRITING_ERROR);
        }
    }


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
}
