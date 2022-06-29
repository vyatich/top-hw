package pack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RunnableClient implements Runnable{
    static Socket socket;
    final String ADR = "localhost";
    final int PORT = 3030;


    public RunnableClient() {
        try {
            socket = new Socket(ADR, PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line;
        try (
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream())) {

            while (true) {
                line = scanner.nextLine();
                oos.writeUTF("clientCommand " + line);
                oos.flush();
                String in = ois.readUTF();
                System.out.println(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

