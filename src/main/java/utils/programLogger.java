package utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class programLogger {
    private static final Logger log = LogManager.getLogger(programLogger.class);

    public static void error(String message) {
        log.error(message);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void display(String message) {
        System.out.println(message);
    }
}
