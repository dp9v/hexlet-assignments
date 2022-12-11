package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String input) {
        var res = new HashMap<String, Integer>();
        for (String word : input.split(" ")) {
            if(Objects.equals(word, "")) {
                continue;
            }
            res.put(word, res.getOrDefault(word, 0) + 1);
        }
        return res;
    }

    public static String toString(Map<String, Integer> input) {
        input.remove("");
        if (input.isEmpty()) {
            return "{}";
        }
        var res = new StringBuilder().append("{\n");
        for (String key : input.keySet()) {
            res.append("  %s: %d\n".formatted(key, input.get(key)));
        }
        res.append("}");
        return res.toString();
    }
}
//END
