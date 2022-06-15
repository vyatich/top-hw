package packClient;

import java.io.*;
import java.net.Socket;

public class Main {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static BufferedReader console;
    public static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("localhost", PORT);
            console = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Соединение с сервером установлено");
            for (int i = 0; i < 3; i++) {
                System.out.println("Введите любой символ чтобы получить новую цитату и / чтобы завершить");
                String word = console.readLine();//клиент пишет с консоли
                if (!(word.equals("/"))) {
                    writer.write(word);
                    writer.newLine();
                    writer.flush();
                    String lineQuote = reader.readLine();
                    System.out.println("Цитата сгенерирована: " + lineQuote);
                } else {
                    System.out.println("введен /, клиент завершен");
                    reader.close();
                    writer.close();
                    clientSocket.close();
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Клиент был закрыт");
        }
    }
}

