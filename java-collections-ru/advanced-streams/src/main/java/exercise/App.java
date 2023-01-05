package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String data1) {
        return Arrays.stream(data1.split("\n")).sequential()
            .filter(x->x.startsWith("environment="))
            .map(x->x.replace("environment=\"", ""))
            .map(x->x.replace("\"", ""))
            .map(String::trim)
            .map(x->x.split(","))
            .flatMap(x-> Arrays.stream(x).sequential())
            .filter(x->x.startsWith("X_FORWARDED_"))
            .map(x->x.replace("X_FORWARDED_", ""))
            .collect(Collectors.joining(","));
    }
}
//END
