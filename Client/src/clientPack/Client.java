package clientPack;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket server;

    public Client(String host, int port) {
        try {
            this.server = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try (BufferedWriter writer =
                     new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(server.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            System.out.println("[CLIENT]");
            System.out.println("Connected to Server! Enter /get to generate a quote.");
            String usersInput, serverInput = "";
            while (true) {
                if ((usersInput = sc.nextLine()).equals("/get")) {
                    writer.write(usersInput);
                    writer.newLine();
                    writer.flush();
                    serverInput = reader.readLine();
                    System.out.println(serverInput);
                } else
                    System.out.println("Wrong command. Enter /get to generate a quote.");
                if (serverInput.contains("Error"))
                    System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
