package serverPack;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Server {

    static File log = new File("log.json");
    final static Random random = new Random();
    static final List<String> quotes = Arrays.asList("The journey of a thousand miles begins with one step. -Lao Tzu",
            "That which does not kill us makes us stronger. -Friedrich Nietzsche",
            "Life is what happens when you’re busy making other plans. -John Lennon",
            "When the going gets tough, the tough get going. -Joe Kennedy",
            "You must be the change you wish to see in the world. -Mahatma Gandhi",
            "You only live once, but if you do it right, once is enough. -Mae West",
            "Tough times never last but tough people do. -Robert H. Schuller");

    private ServerSocket server;

    public Server(int port) {
        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        System.out.println("[SERVER]");
        while (true) {
            try (Socket client = server.accept();
                 BufferedWriter writer =
                         new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                 BufferedReader reader =
                         new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                Logger logger = new Logger(log);
                List<String> usedQuotes = new ArrayList<>();
                //
                logger.setConnectTime(LocalDateTime.now());
                logger.setClientName(client.getRemoteSocketAddress().toString());
                //
                System.out.println("Client " + client.getRemoteSocketAddress() + " connected.");
                while (!client.isClosed()) {
                    if (reader.readLine().equals("/get")) {
                        String randomQuote = "";
                        while (usedQuotes.contains(randomQuote) || randomQuote.isEmpty())
                            randomQuote = quotes.get(random.nextInt(quotes.size()));
                        if (usedQuotes.size() >= 4) {
                            logger.setGottenQuotes(usedQuotes);
                            writer.write("Error - Server disconnected you because you've gotten maximum quote count. ");
                            writer.newLine();
                            writer.flush();
                            logger.setDisconnectTime(LocalDateTime.now());
                            client.close();
                        } else {
                            usedQuotes.add(randomQuote);
                            writer.write(randomQuote);
                            writer.newLine();
                            writer.flush();
                        }
                    }
                }
                System.out.println("Client " + client.getRemoteSocketAddress() + " disconnected.");
                logger.logToJson();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
