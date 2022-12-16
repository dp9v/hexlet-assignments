package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> homes, int n) {
        return homes.stream()
            .sorted(Comparator.comparingDouble(Home::getArea))
            .limit(n)
            .map(Home::toString)
            .toList();
    }
}
// END
