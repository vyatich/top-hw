package serverPackM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoggerM {

    private final SimpleDateFormat formater = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
    private String clientAddress;
    private String startTime;
    private final List<String> listOfQuotes = new ArrayList<>();
    private String endTime;

    public LoggerM() {
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public void setStartTime(Date startTime) {
        this.startTime = formater.format(startTime);
    }

    public List<String> getListOfQuotes() {
        return listOfQuotes;
    }

    public void setEndTime(Date endTime) {
        this.endTime = formater.format(endTime);
    }

    @Override
    public String toString() {
        return '\n' + "Адрес клиента: " + clientAddress + '\n' +
                "Время подключения: " + startTime + '\n' +
                "Цитаты: " + listOfQuotes + '\n' +
                "Время отключения: " + endTime + '\n';
    }

    public static void writeInLogFile(LoggerM logger) {
        File loggingDirectory = new File("ServerMulty/src/serverPackM/logFile.txt");

        try (BufferedWriter logInFile = new BufferedWriter(new FileWriter(loggingDirectory, true))) {
            logInFile.write(String.valueOf(logger));
            logInFile.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}