package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Class to creact Logger
 */
public class Logger {
    /**
     * Initializes Log Manager from logging.properties
     * @throws IOException
     */
    public static void initializeLogManager() throws IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("./src/Resource/logging.properties"));
    }
}
