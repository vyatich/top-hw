package pack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class RunnableClient implements Runnable{
    private String clientName;
    static Socket socket;
    final String ADR = "localhost";
    final int PORT = 3030;


    public RunnableClient(String name) {
        this.clientName = name;
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
        try (DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream());
             DatagramSocket datagramSocket = new DatagramSocket()) {

            while (true) {
                line = scanner.nextLine();
                oos.writeUTF(clientName + ":" + "clientCommand " + line);
                oos.flush();
//                String in = ois.readUTF();
//                System.out.println(in);
                byte[] buf = new byte[10240];
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length/*, socket.getLocalSocketAddress()*/);
                datagramSocket.receive(datagramPacket);
                byte[] input = datagramPacket.getData();
                String inputString = new String(input, 0, datagramPacket.getLength());
                System.out.println(inputString);
                System.out.println("Hi");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

