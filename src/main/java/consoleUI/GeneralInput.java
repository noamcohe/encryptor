package consoleUI;
import java.nio.file.Path;

public interface GeneralInput {
    int getInt();
    Path getPath(String message);
    int getKey();
    int getNumByRange(int range);
}
