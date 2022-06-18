package pack;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyListener implements Runnable{
    Socket client = null;
    BufferedWriter bWriter = null;
    BufferedReader bReader = null;
    String msg = "";

    public MyListener(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        Quotes quotes = new Quotes();
        Logger logger = new Logger();

        try {
            System.out.println("client " + this.client.getRemoteSocketAddress() + " connected");
            bWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bWriter.flush();

            logger.setSocketName(client.getRemoteSocketAddress().toString());
            logger.setConnectedTime(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));

            bReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            do {
                msg = bReader.readLine();
                if (msg.equals("/exit")) {
                    break;
                }
                if (logger.getQuotesList().size() < 5) {
                    if (msg.equals("/get")) {
                        String quote = quotes.getQuotes((int) (Math.random() * 8));
                        logger.getQuotesList().add(quote);
                        bWriter.write(quote);
                    } else {
                        bWriter.write("For a quote enter /get");
                    }
                    bWriter.newLine();
                    bWriter.flush();
                } else {
                    bWriter.write("Server disconnected client");
                    bWriter.flush();
                    client.close();
                    break;
                }
            } while (!msg.equals("/exit"));
            System.out.println("client " + logger.getSocketName() + " disconnected");
            logger.setDisconnectedTime(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
            LogToFile.getInstance().write(logger + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}