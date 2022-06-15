package serverPackM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ServerMulty extends Thread {
    private final Socket socket;
    private final DataInputStream is;
    private final DataOutputStream os;


    public ServerMulty(Socket clientSocket) {
        socket = clientSocket;
        try {
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        start();

    }

    public void run() {
        LoggerM logger = new LoggerM();
        final int MAXCOUNTQUOTES = 4;

        logger.setClientAddress(String.valueOf(socket.getRemoteSocketAddress()));
        logger.setStartTime(new Date());

        try {
            while (!(logger.getListOfQuotes().size() == MAXCOUNTQUOTES)) {

                if (logger.getListOfQuotes().size() == (MAXCOUNTQUOTES - 1)) {
                    os.writeUTF("Внимание: превышено максимальное количество цитат, соединение будет разорвано!!!");
                    os.flush();
                    sleep(1500);
                    socket.shutdownInput();
                }
                String line = is.readUTF();
                System.out.print("Клиент: " + socket.getRemoteSocketAddress() + " Запрос: " + line + "\n");
                String quote = RandomM.randomQuotesM();

                os.writeUTF(quote);
                os.flush();

                logger.getListOfQuotes().add(quote);
            }
        } catch (IOException | InterruptedException | IllegalMonitorStateException e) {
            System.out.println("Сообщение: " + e.getMessage());
        }
        logger.setEndTime(new Date());
        LoggerM.writeInLogFile(logger);
    }
}


