package DalObject;
import java.io.File;
import java.util.function.Predicate;

public class DalObject {

    public void createFile(File file) {
        if (!DataSource.filesList.contains(file)) {
            DataSource.filesList.add(file);
        }
    }
}
