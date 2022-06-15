package clientPack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        new Client2("127.0.0.1", 8122).startClient();
    }

    private final String ADDRESS;

    private final int SERVERPORT;

    public Client2(String address, int serverPort) {
        this.ADDRESS = address;
        this.SERVERPORT = serverPort;
    }

    public void startClient() {
        try (Socket clientSocket = new Socket(ADDRESS, SERVERPORT)) {
            System.out.println("Клиент запущен");

            new Authentication().runAuthentication();

            DataInputStream is = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            String line;
            while (true) {
                System.out.print("Укажите запрос: ");
                line = sc.nextLine();
                os.writeUTF(line);
                os.flush();
                line = is.readUTF();
                System.out.printf("Ответ от сервера:  %s", line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Соединение завершено");
        }
    }
}