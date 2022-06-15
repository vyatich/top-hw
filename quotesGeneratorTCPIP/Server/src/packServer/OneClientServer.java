package packServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OneClientServer {
    private static Quotes quote;
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedWriter writer;
    private static BufferedReader reader;
    public static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");
            clientSocket = server.accept();
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//читаем с консоли
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));//передаем цитату
            System.out.println("клиент " + clientSocket.getRemoteSocketAddress() + " подключен в" + getTime());
            for (int i = 0; i < 3; i++) {//3 попытки генерации для клиента
                String msg = reader.readLine();
                if (!(msg.equals("/"))) {
                    String quote;
                    quote = generateQuote();
                    writer.write(quote);
                    writer.newLine();
                    writer.flush();
                } else {
                    System.out.println("Клиент отключен в " + getTime());
                    System.out.println("Клиент был закрыт по причине некорректного запроса");
                    System.exit(0);
                }
            }
            writer.write("Слишком много попыток, переподключитесь\"");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private static String getTime() {
        String timeStamp = new SimpleDateFormat("HH:mm:ss dd.MM.yy").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public static int getRandom() {
        int min = 0;
        int max = 6;
        int num = (int) Math.floor((Math.random() * ((max - min) + 1)) + min);
        return num;
    }

    public static String generateQuote() {
        String[] line = quote.getQuote();
        int index = getRandom();
        String quote = line[index];
        return quote;
    }
}
