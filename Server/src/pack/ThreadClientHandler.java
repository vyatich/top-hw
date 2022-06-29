package pack;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ThreadClientHandler implements Runnable{
    private static Socket clientDialog;
    private static QuoteGenerator quote = new QuoteGenerator();


    public ThreadClientHandler(Socket client) {
        ThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {
        File log = new File("connectionLog.json" );
        Gson gson =new Gson();
        List<String> quotes = new ArrayList<>();
        ConnectionLog connectionLog = new ConnectionLog();
        connectionLog.setClient(clientDialog.getRemoteSocketAddress().toString());
        connectionLog.setDateIn(OffsetDateTime.now());
        try {
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            while (!clientDialog.isClosed()) {
                String entry = in.readUTF();
                if (entry.equalsIgnoreCase("clientCommand /q")) {
                    out.writeUTF("Server reply - " + entry + " - OK");
                    connectionLog.setQuotes(quotes);
                    connectionLog.setDateOut(OffsetDateTime.now());
                    connectionLog.addLog(connectionLog, log, gson);
//                    clientDialog.close();
                    break;
                }
                String message = quote.getQuote();
                out.writeUTF(message);
                quotes.add(message);
                out.flush();
            }
            in.close();
            out.close();
            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

