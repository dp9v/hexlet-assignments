package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        var values = storage.toMap();
        for (String s : values.keySet()) {
            storage.unset(s);
        }
        for (Entry<String, String> entry : values.entrySet()) {
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
