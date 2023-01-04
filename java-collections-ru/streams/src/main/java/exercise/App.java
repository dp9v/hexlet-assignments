package exercise;

import java.util.List;
import java.util.Set;

// BEGIN
public class App {

    public final static Set<String> FREE_DOMENS = Set.of("gmail.com", "yandex.ru", "hotmail.com");
    public static long getCountOfFreeEmails(List<String> emailsList) {
        return emailsList.stream()
            .map(e->e.split("@")[1])
            .filter(FREE_DOMENS::contains)
            .count();
    }
}
// END
