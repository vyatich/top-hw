package packServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MultiThreadServer {
    private static Quotes quote;
    private static ServerSocket server;

    public static final int PORT = 8080;
    private static Socket clientSocket;

    private class ServerThread extends Thread {
        public ServerThread(Socket clientSocket) {
            MultiThreadServer.clientSocket = clientSocket;
        }
    }

    //метод, в котором в бесконечном цикле сервер принимает новое сокетное подключение от клиента
    protected void acceptServer() {
        while (true) {
            try {
                clientSocket = server.accept();
                new ServerThread(clientSocket).start();
                System.out.println("Клиент " + clientSocket.getRemoteSocketAddress() + " подключился в " + getTime());
            } catch (Exception e) {
                System.out.println("Связь с сервером потеряна.\n");
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MultiThreadServer serverNew = new MultiThreadServer();
        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");
            serverNew.acceptServer();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));//передаем цитату
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//читаем с консоли
            try {
                while (!clientSocket.isClosed()) {
                    for (int i = 0; i < 3; i++) {//3 попытки генерации для клиента
                        String msg = reader.readLine();
                        if (!(msg.equals("/"))) {
                            String quote;
                            quote = generateQuote();
                            writer.write(quote);
                            writer.newLine();
                            writer.flush();
                        }
                    }
                }writer.write("Клиент закрыт в " + getTime());
                writer.flush();
            } catch (IOException e) {
                writer.write("Клиент закрыт в " + getTime());
                writer.flush();
                writer.close();
                reader.close();
                clientSocket.close();
                server.close();
            }
        } catch (Exception e) {
            System.out.println("Не удалось запустить сервер.\n");
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
