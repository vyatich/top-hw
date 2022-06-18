package pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quotes {
    private final List<String> quotes;

    public Quotes() {
        quotes = new ArrayList<>(Arrays.asList(
                "Wise men speak because they have something to say; fools because they have to say something.",
                "Chop your own wood and it will warm you twice.",
                "I don’t care what you think about me. I don’t think about you at all.",
                "Work hard to get what you like, otherwise you'll be forced to just like what you get.",
                "In the End, we will remember not the words of our enemies, but the silence of our friends.",
                "When you do something noble and beautiful and nobody noticed, do not be sad. For the sun every morning is a beautiful spectacle and yet most of the audience still sleeps.",
                "The weak can never forgive. Forgiveness is the attribute of the strong.",
                "Success is the ability to go from failure to failure without losing your enthusiasm."
        ));
    }

    public String getQuotes(int number) {
        return quotes.get(number);
    }
}