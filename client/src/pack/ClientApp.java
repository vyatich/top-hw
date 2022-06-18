package pack;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    Socket client = null;
    BufferedWriter bWriter = null;
    BufferedReader bReader = null;
    int port = 8081;
    String msg = "";

    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp();
        clientApp.setConnection();
    }

    void setConnection() {
        try {
            client = new Socket("127.0.0.1", port);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Connected to server, enter \"/get\" to get quotes");
            bWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bWriter.flush();
            bReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            do {
                msg = scanner.nextLine();
                if (msg == null) {
                    msg = "";
                }
                bWriter.write(msg);
                bWriter.newLine();
                if (!msg.equals("/exit")) {
                    bWriter.flush();
                    String requestServer = bReader.readLine();
                    System.out.println("server> " + requestServer);
                    if (requestServer.equals("Server disconnected client")) {
                        break;
                    }
                }
            } while (!msg.equals("/exit") && !client.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bWriter != null)
                    bWriter.close();
                if (bReader != null)
                    bReader.close();
                if (client != null)
                    client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Client disconnected");
    }
}