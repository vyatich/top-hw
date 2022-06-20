package serverPack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logger {
    private String clientName;
    private String connectTime;
    private List<String> gottenQuotes;
    private String disconnectTime;

    private final File log;

    public Logger(File log) {
        this.log = log;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(LocalDateTime connectTime) {
        this.connectTime = dateTimeFormat(connectTime);
    }

    public List<String> getGottenQuotes() {
        return gottenQuotes;
    }

    public void setGottenQuotes(List<String> gottenQuotes) {
        this.gottenQuotes = gottenQuotes;
    }

    public String getDisconnectTime() {
        return disconnectTime;
    }

    public void setDisconnectTime(LocalDateTime disconnectTime) {
        this.disconnectTime = dateTimeFormat(disconnectTime);
    }

    public void logToJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = FileUtils.readFileToString(log, "utf8");
            ArrayList<Logger> loggersList = new ArrayList<>();
            //
            if (json.length() > 0) {
                Logger[] loggers = gson.fromJson(json, Logger[].class);
                loggersList = new ArrayList<>(Arrays.asList(loggers));
            }
            loggersList.add(this);
            json = gson.toJson(loggersList);
            FileUtils.write(log, json, "utf8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String dateTimeFormat(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
