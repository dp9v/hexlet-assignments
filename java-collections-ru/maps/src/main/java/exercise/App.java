package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String input) {
        var res = new HashMap<String, Integer>();
        for (String word : input.split(" ")) {
            res.put(word, res.getOrDefault(word, 0) + 1);
        }
        return res;
    }
}
//END
