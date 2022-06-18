package pack;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private String socketName;
    private String connectedTime;
    private String disconnectedTime;
    private final List<String> quotesList = new ArrayList<>();

    public Logger() {
    }

    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(String socketName) {
        this.socketName = socketName;
    }

    public void setConnectedTime(String connectedTime) {
        this.connectedTime = connectedTime;
    }

    public void setDisconnectedTime(String disconnectedTime) {
        this.disconnectedTime = disconnectedTime;
    }

    public List<String> getQuotesList() {
        return quotesList;
    }

    @Override
    public String toString() {
        return "Log {\n" +
                "socketName='" + socketName + '\'' +
                ", \nconnectedTime=" + connectedTime +
                ", \ndisconnectedTime=" + disconnectedTime +
                ", \nquotesList=" + quotesList + "\n" +
                '}';
    }
}