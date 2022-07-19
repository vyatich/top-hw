package pack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadClientHandler implements Runnable{
    private static Socket clientDialog;
    private static DatagramSocket datagramSocket;
    private static ConcurrentHashMap<String, String> roundResult;
    private static String answer;
    private Battle battle = new Battle();
/*    private final int PORT;*/


    public ThreadClientHandler(Socket client, ConcurrentHashMap<String, String> roundResult/*, int port*/, DatagramSocket datagramSocket) {
       /* PORT = port;*/
        ThreadClientHandler.clientDialog = client;
        ThreadClientHandler.roundResult = roundResult;
        ThreadClientHandler.datagramSocket = datagramSocket;

    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            while (!clientDialog.isClosed()) {
                String entry = in.readUTF();
                List<String> entryList = List.of(entry.split(":"));
                String entryAddress = entryList.get(0);
                String entryAnswer = entryList.get(1);
                /*if (entryAnswer.equalsIgnoreCase("clientCommand /q")) {
                    out.writeUTF("Server reply - " + entry + " - OK");
                    clientDialog.close();
                    break;
                } else */if (entryAnswer.equalsIgnoreCase("clientCommand камень")
                        || entryAnswer.equalsIgnoreCase("clientCommand ножницы")
                        || entryAnswer.equalsIgnoreCase("clientCommand бумага")) {
                    answer = entryAnswer;
                    roundResult.put(entryAddress,
                            answer);
                    System.out.println(roundResult);
                    if (roundResult.size() == 2) {
                        String result = battle.start(roundResult);
                        System.out.println(result);
//                        out.writeUTF(result);
//                        out.flush();
                        byte[] bytes = result.getBytes();
                        DatagramPacket datagramPacket =
                                new DatagramPacket(bytes, bytes.length, clientDialog.getInetAddress(), clientDialog.getPort());
                        datagramSocket.send(datagramPacket);
                        roundResult.clear();

//                        result = null;
                    } else {
//                    Thread.sleep(10000);

//                    String result = "Hello Anton!!!";
//                    byte[] bytes = result.getBytes();
//                    DatagramPacket datagramPacket =
//                            new DatagramPacket(bytes, bytes.length,
//                                    /*clientDialog.getInetAddress()*/InetAddress.getByName("127.0.0.1"),
//                                    clientDialog.getPort());
//                    Thread.sleep(10000);
//                    datagramSocket.send(datagramPacket);
//                        out.writeUTF("ответ принят");
//                        out.flush();
                    }
                } else {
                    out.writeUTF("введены не корректные данные, попробуйте ещё раз");
                }
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

