import java.io.IOException;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8080;
    private static final String ADDRESS = "127.0.0.1";

    public static void main(String[] args) {
        try (Phone clientPhone = new Phone(ADDRESS, PORT);
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String request = scanner.nextLine();
                clientPhone.write(request);
                try {
                    String response = clientPhone.read();
                    System.out.println(response);
                } catch (RuntimeException e) {
                    break;
                }
            }
            System.out.println("SERVER is disconnected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
