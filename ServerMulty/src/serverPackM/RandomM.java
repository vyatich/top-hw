package serverPackM;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomM {

    public static String randomQuotesM() {
        File quote = new File("ServerMulty/src/serverPackM/quotes.txt");
        int count = 1;
        List<String> quotes = new ArrayList<>();
        String read;

        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(quote)))) {
            while ((read = br.readLine()) != null) {
                quotes.add(read);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        Collections.shuffle(quotes);

        return String.valueOf(quotes.get(count));
    }
}
