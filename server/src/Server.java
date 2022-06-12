import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

public class Server {
    private static final int PORT = 8080;
    private static final Quotes quotes = new Quotes();

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("SERVER started");

            while (true) {
                Phone phone = new Phone(server);
                    new Thread(() -> {
                        ClientLog clientLog = new ClientLog();
                        clientLog.setSocketName(phone.getSocket().getRemoteSocketAddress().toString());
                        clientLog.setConnectedTime(new Date());
                        while (!phone.getSocket().isClosed()) {
                            if (clientLog.getQuotesList().size() < 5) {
                                String text;
                                try {
                                    text = phone.read();
                                } catch (RuntimeException e) {
                                    clientLog.setDisconnectedTime(new Date());
                                    WriteLoggToFile.getInstance().write(clientLog);
                                    try{ phone.close(); } catch (IOException es) {es.printStackTrace();}
                                    break;
                                }
                                if (text.equals("/get")) {
                                    String quote = quotes.getQuotes((int) (Math.random() * 6));
                                    clientLog.getQuotesList().add(quote);
                                    phone.write(quote);
                                } else {
                                    phone.write("Please, write /get to give quotes");
                                }
                            } else {
                                phone.write("q");
                                clientLog.setDisconnectedTime(new Date());
                                WriteLoggToFile.getInstance().write(clientLog);
                                try{ phone.close(); } catch (IOException e) {e.printStackTrace();}
                                break;
                            }
                        }
                    }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
