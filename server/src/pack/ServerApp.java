package pack;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    ServerSocket serverSocket = null;
    Socket client = null;
    int maxCount = 5;
    int count = 0;
    int port = 8081;

    public static void main(String[] args) {
        ServerApp serverApp = new ServerApp();
        System.out.println("Server started. Waiting connection...");

        serverApp.createConnection();
    }

    void createConnection() {
        try {
            serverSocket = new ServerSocket(port, maxCount);

            while (count <= maxCount) {
                count++;
                client = serverSocket.accept();
                MyListener myListener = new MyListener(client);
                Thread thread = new Thread(myListener);
                thread.setDaemon(true);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}