import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteLoggToFile {

    private static final File LOGG_FILE = new File("loggFile.json");
    private static WriteLoggToFile INSTANCE;

    private WriteLoggToFile() {
    }

    public void write(ClientLog clientLog) {
        try {
            FileWriter fileWriter = new FileWriter(LOGG_FILE, true);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(clientLog));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WriteLoggToFile getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WriteLoggToFile();
        }
        return INSTANCE;
    }
}
