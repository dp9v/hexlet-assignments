package exercise;

import exercise.daytimes.Daytime;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
@RequiredArgsConstructor
public class WelcomeController {
    private final Daytime daytime;
    private final Meal meal;


    @GetMapping("/daytime")
    public String getDaytime() {
        var mealName = meal.getMealForDaytime(daytime.getName());
        return "It is %s now. Enjoy your %s".formatted(daytime.getName(), mealName);
    }
}
// END
