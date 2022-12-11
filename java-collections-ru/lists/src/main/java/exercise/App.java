package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static boolean scrabble(String s1, String word) {
        var symbols = new ArrayList<Character>();
        for (char c : s1.toLowerCase().toCharArray()) {
            symbols.add(c);
        }
        word = word.toLowerCase();
        for (Character c : word.toCharArray()) {
            if (symbols.contains(c)) {
                symbols.remove(c);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
