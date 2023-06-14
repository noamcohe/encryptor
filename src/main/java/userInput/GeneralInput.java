package userInput;
import java.nio.file.Path;

public interface GeneralInput {
    int getInt();
    Path getPath(String message);
    int getNumByRange(int range);
}
