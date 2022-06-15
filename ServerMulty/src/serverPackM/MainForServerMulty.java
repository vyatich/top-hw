package serverPackM;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainForServerMulty {
    static final int PORT = 8122;
    public static void main(String[] args) throws IOException {

        try (ServerSocket s = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту: " + PORT);
            System.out.println("Ожидание подключения");
            while (true) {
                Socket socket = s.accept();
                System.out.println("Соединение с клиентом: " + socket.getRemoteSocketAddress() + " установлено");
                new ServerMulty(socket);
                if (socket.isInputShutdown())
                System.out.println("Клиент: " + socket.getRemoteSocketAddress() + " отключён");
            }
        }

    }
}
