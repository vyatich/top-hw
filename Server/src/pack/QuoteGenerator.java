package pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuoteGenerator {
    private Random rd = new Random();
    List<String> quotes = new ArrayList<>(Arrays.asList(
            "хорошо живёт на свете Винни Пух!",
            "кто ходит в гости по утрам, тот поступает мудро!",
            "сим сим откройся!",
            "щас спою!",
            "работа не волк, в лес не убежит...",
            "утро вечера мудренее...",
            "что такое не везёт и как с этим бороться?"
    ));

    public String getQuote() {
        int i = rd.nextInt(6);
        return quotes.get(i);
    }


}
