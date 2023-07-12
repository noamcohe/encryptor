package userInput;
import java.nio.file.Path;


public interface GeneralInput {
    int getInt();
    Path getPath(String message, boolean isKeyFile);
    int getNumByRange(int range);
    void close();
}
