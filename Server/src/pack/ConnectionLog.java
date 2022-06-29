package pack;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

public class ConnectionLog {
    private String client;
    private OffsetDateTime dateIn;
    private List<String> quotes;
    private OffsetDateTime dateOut;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public OffsetDateTime getDateIn() {
        return dateIn;
    }

    public void setDateIn(OffsetDateTime dateIn) {
        this.dateIn = dateIn;
    }

    public List<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }


    public OffsetDateTime getDateOut() {
        return dateOut;
    }

    public void setDateOut(OffsetDateTime dateOut) {
        this.dateOut = dateOut;
    }

    public void addLog (ConnectionLog o, File connectionLog, Gson gson){
        String log = gson.toJson(o);
        try {
            FileUtils.write(connectionLog, log, "utf8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
