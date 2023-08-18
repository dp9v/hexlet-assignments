package exercise.dto;

public record WeatherDto(
    String name,
    int temperature,
    String cloudy,
    int wind,
    int humidity
) {
}
