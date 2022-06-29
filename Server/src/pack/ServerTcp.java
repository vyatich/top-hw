package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTcp {
    public static void main(String[] args) {
        new ServerTcp().start();
    }

    private final int PORT = 3030;
    private static ExecutorService executeIt = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    final void start() {
        System.out.println("[SERVER]");
        try (ServerSocket server = new ServerSocket(PORT);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            while (!server.isClosed()) {
                if (br.ready()) {
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("/q")) {
                        server.close();
                        break;
                    }
                }
                Socket client = server.accept();
                executeIt.execute(new ThreadClientHandler(client));
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

