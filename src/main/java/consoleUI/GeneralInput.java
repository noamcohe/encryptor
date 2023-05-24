package consoleUI;
import org.jetbrains.annotations.NotNull;
import java.nio.file.Path;
import enums.Choice;

public interface GeneralInput {
    int getInt();
    @NotNull Path getPath();
    int getKey();
    Choice getChoice();
}
