package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
@AllArgsConstructor
class Car {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final int id;
    private final String brand;
    private final String model;
    private final String color;
    private final User owner;

    // BEGIN

    @SneakyThrows
    public String serialize() {
        return OBJECT_MAPPER.writeValueAsString(this);
    }

    @SneakyThrows
    public static Car unserialize(String input) {
        return OBJECT_MAPPER.readValue(input, Car.class);
    }
    // END
}
