package clientPack;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Scanner;

public class Authentication {
    public void runAuthentication() {
        Scanner scan;
        try {
            scan = new Scanner(Paths.get("Clients/src/clientPack/forAuthentication.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner keyboard = new Scanner(System.in);
        String user = scan.nextLine();
        String pass = scan.nextLine();

        byte[] decUser = Base64.getDecoder().decode(user);
        byte[] decPass = Base64.getDecoder().decode(pass);
        String decodedUser = new String(decUser);//admin
        String decodedPass = new String(decPass);//root

        String inpUser;
        String inpPass;
        do {
            System.out.println("Укажите логин");
            inpUser = keyboard.nextLine();
            System.out.println("Укажите пароль");
            inpPass = keyboard.nextLine();

            if (inpUser.equals(decodedUser) && inpPass.equals(decodedPass)) {
                System.out.print("Авторизация успешна\n");
                break;
            } else {
                System.out.print("Авторизация не успешна\n");
            }
        } while (true);
    }
}
