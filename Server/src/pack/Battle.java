package pack;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Battle {

    public String start(ConcurrentHashMap result){
        Set<String> keys = result.keySet();
        String keysString = keys.toString();
        keysString = keysString.replaceAll("[\\[\\]]", "");
        List<String> keysList = List.of(keysString.split(",\s"));
        String key1 = keysList.get(0);
        String key2 = keysList.get(1);
//        List<Map.Entry<String, String>> keys1 = result.entrySet();
        String s1 = (String) result.get(key1);
        String s2 = (String) result.get(key2);
        if (s1.equalsIgnoreCase("clientCommand камень") && s2.equalsIgnoreCase("clientCommand бумага"))
            return "Победил " + key2 + " : " + s2;
        if (s1.equalsIgnoreCase("clientCommand камень") && s2.equalsIgnoreCase("clientCommand ножницы"))
            return "Победил " + key1 + " : " + s1;
        if (s1.equalsIgnoreCase("clientCommand бумага") && s2.equalsIgnoreCase("clientCommand камень"))
            return "Победил " + key1 + " : " + s1;
        if (s1.equalsIgnoreCase("clientCommand бумага") && s2.equalsIgnoreCase("clientCommand ножницы"))
            return "Победил " + key2 + " : " + s2;
        if (s1.equalsIgnoreCase("clientCommand ножницы") && s2.equalsIgnoreCase("clientCommand камень"))
            return "Победил " + key2 + " : " + s2;
        if (s1.equalsIgnoreCase("clientCommand ножницы") && s2.equalsIgnoreCase("clientCommand бумага"))
            return "Победил " + key1 + " : " + s1;
        else {
            return "Ничья" + key1 + " : " + s1 + " - " + key2 + " : " + s2;
        }
    }

}

