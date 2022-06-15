package serverPack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerSingle {
    private final int PORT;

    public ServerSingle(int port) {
        PORT = port;
    }

    public void startServer() {
        Logger logger = new Logger();
        final int MAXCOUNTQUOTES = 4;

        System.out.println("Ожидание подключения\n");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Сервер запущен\n");

            logger.setClientAddress(String.valueOf(clientSocket.getRemoteSocketAddress()));
            logger.setStartTime(new Date());

            while (!(logger.getListOfQuotes().size() == MAXCOUNTQUOTES)) {
                DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());

                if (logger.getListOfQuotes().size() == (MAXCOUNTQUOTES - 1)) {
                    os.writeUTF("Внимание: превышено максимальное количество цитат, соединение будет разорвано!!!");
                    os.flush();
                }
                String line = is.readUTF();
                System.out.print("Клиент: " + clientSocket.getRemoteSocketAddress() + " Запрос: " + line + "\n");
                String quote = Random.randomQuotes();

                os.writeUTF(quote);
                os.flush();

                logger.getListOfQuotes().add(quote);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        logger.setEndTime(new Date());
        Logger.writeInLogFile(logger);
    }
}