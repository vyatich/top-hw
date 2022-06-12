
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientLog {

    private String socketName;
    private Date connectedTime;
    private List<String> quotesList = new ArrayList<>();
    private Date disconnectedTime;

    public ClientLog() {
    }

    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(String socketName) {
        this.socketName = socketName;
    }

    public Date getConnectedTime() {
        return connectedTime;
    }

    public void setConnectedTime(Date connectedTime) {
        this.connectedTime = connectedTime;
    }

    public List<String> getQuotesList() {
        return quotesList;
    }

    public void setQuotesList(List<String> quotesList) {
        this.quotesList = quotesList;
    }

    public Date getDisconnectedTime() {
        return disconnectedTime;
    }

    public void setDisconnectedTime(Date disconnectedTime) {
        this.disconnectedTime = disconnectedTime;
    }

    @Override
    public String toString() {
        return "ClientLog{\n" +
                "socketName='" + socketName + '\'' +
                ",\nconnectedTime=" + connectedTime +
                ",\nquotesList=" + quotesList +
                ",\ndisconnectedTime=" + disconnectedTime + "\n" +
                '}';
    }
}
