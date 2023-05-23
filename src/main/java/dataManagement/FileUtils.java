package dataManagement;
import consoleUI.HelpConstants;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileUtils {

    /**
     * Read all the given file data into a string.
     * @param filePath path of the file to read.
     * @return String contains all the file data.
     */
    public static @NotNull String readFileToStr(Path filePath) {
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(HelpConstants.READING_ERROR);
            return "";
        }
    }


    /**
     * create an new output file, and write into this file the given string.
     * @param filePath Path for the new file.
     * @param newData string contains all the data needs to written to the output file.
     */
    public static void writeStrToFile(Path filePath, @NotNull String newData) {
        try {
            Files.createFile(filePath);
            Files.write(filePath, newData.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println(HelpConstants.WRITING_ERROR);
        }
    }
}
