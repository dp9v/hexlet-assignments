package exercise.controller;

import exercise.CityNotFoundException;
import exercise.dto.CityDto;
import exercise.dto.WeatherDto;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    @GetMapping("/cities/{id}")
    public WeatherDto getWeather(@PathVariable Long id) {
        var city = cityRepository.findById(id)
            .orElseThrow(() -> new CityNotFoundException(""));

        return weatherService.getWeather(city.getName());
    }

    @GetMapping("/search")
    public List<CityDto> search(@RequestParam(required = false, defaultValue = "") String name) {
        if (name.isEmpty()) {
            return cityRepository.findAllByOrderByNameAsc()
                .stream()
                .map(c->new CityDto(c.getName(), weatherService.getWeather(c.getName()).temperature()))
                .toList();
        }
        return cityRepository.findByNameIgnoreCaseStartingWith(name)
            .stream()
            .map(c->new CityDto(c.getName(), weatherService.getWeather(c.getName()).temperature()))
            .toList();
    }
    // END
}

