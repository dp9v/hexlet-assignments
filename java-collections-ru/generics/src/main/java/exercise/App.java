package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(
        List<Map<String, String>> books, Map<String, String> where
    ) {
        var result = new ArrayList<Map<String, String>>();
        for (Map<String, String> book : books) {
            if (isMatch(book, where)) {
                result.add(book);
            }
        }
        return result;
    }

    public static boolean isMatch(Map<String, String> bookInfo, Map<String, String> filters) {
        for (Entry<String, String> filter : filters.entrySet()) {
            if (!filter.getValue().equals(bookInfo.get(filter.getKey()))) {
                return false;
            }
        }
        return true;
    }
}
//END
