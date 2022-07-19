package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.*;

public class ServerTcp {
    public static void main(String[] args) {
        new ServerTcp().start();
    }

    private final ConcurrentHashMap<String, String> roundResult = new ConcurrentHashMap<>();
//    ConcurrentLinkedQueue<>

    private final int PORT = 3030;
    private static ExecutorService executeIt = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    final void start() {
        System.out.println("[SERVER]");
        try (ServerSocket server = new ServerSocket(PORT);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             DatagramSocket datagramSocket = new DatagramSocket(3031)){

            while (!server.isClosed()) {
                if (br.ready()) {
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("/q")) {
                        server.close();
                        break;
                    }
                }
                Socket client = server.accept();
                executeIt.execute(new ThreadClientHandler(client, roundResult/*, PORT*/, datagramSocket));
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

