package exercise;

import java.time.LocalDateTime;
import java.time.LocalTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {
    private static final LocalTime NIGHT = LocalTime.of(23, 0);
    private static final LocalTime MORNING = LocalTime.of(6, 0);
    private static final LocalTime DAY = LocalTime.of(12, 0);
    private static final LocalTime EVENING = LocalTime.of(18, 0);

    @Bean
    public Daytime daytime() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isAfter(NIGHT) && currentTime.isBefore(MORNING)) {
            return new Night();
        } else if (currentTime.isBefore(DAY)) {
            return new Morning();
        } else if (currentTime.isBefore(EVENING)) {
            return new Day();
        } else {
            return new Evening();
        }
    }
}
// END
