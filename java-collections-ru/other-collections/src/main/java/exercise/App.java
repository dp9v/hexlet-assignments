package exercise;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
public class App {
    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        var res = new LinkedHashMap<String, String>();
        var allKeys = new HashSet<String>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        for (String key : allKeys) {
            if(map1.containsKey(key) && !map2.containsKey(key)) {
                res.put(key, "deleted");
                continue;
            }
            if(map2.containsKey(key) && !map1.containsKey(key)) {
                res.put(key, "added");
                continue;
            }
            if(map1.get(key).equals(map2.get(key))) {
                res.put(key, "unchanged");
            } else {
                res.put(key, "changed");
            }
        }
        return res;
    }
}
//END
