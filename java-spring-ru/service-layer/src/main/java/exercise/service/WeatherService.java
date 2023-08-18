package exercise.service;

import exercise.HttpClient;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.WeatherDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;
    ObjectMapper objectMapper;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
        this.objectMapper = new ObjectMapper();
    }

    // BEGIN
    @SneakyThrows
    public WeatherDto getWeather(String cityName) {
        var weather = client.get("http://weather/api/v2/cities/" + cityName);
        return objectMapper.readValue(weather, WeatherDto.class);
    }
    // END
}
