package exercise;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

// BEGIN
public class App {

    @SneakyThrows
    public static void save(Path tempPath, Car car) {
        var carJson = car.serialize();
        Files.writeString(tempPath, carJson);
    }

    @SneakyThrows
    public static Car extract(Path fixturePath) {
        return Car.unserialize(Files.readString(fixturePath));
    }
}
// END
