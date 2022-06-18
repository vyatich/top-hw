package pack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogToFile {
    private static final File LOG_FILE = new File("log.txt");
    private static LogToFile INSTANCE;

    public LogToFile() {
    }

    public void write(String log) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE, true);
            fileWriter.write(log);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LogToFile getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogToFile();
        }
        return INSTANCE;
    }
}