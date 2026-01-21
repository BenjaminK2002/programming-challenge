package de.bcxp.challenge;

import de.bcxp.challenge.weather.model.DailyWeather;
import de.bcxp.challenge.weather.service.WeatherService;
import org.junit.jupiter.api.Test;

// Example using JUnit 5
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private final WeatherService service = new WeatherService();

    @Test
    void shouldFindSmallestSpread_NormalCase() {
        List<DailyWeather> data = List.of(
                new DailyWeather(1, 30.0, 10.0), // Spread 20
                new DailyWeather(2, 25.0, 20.0), // Spread 5 (Winner)
                new DailyWeather(3, 40.0, 10.0)  // Spread 30
        );
        Optional<DailyWeather> result = service.findDayWithSmallestSpread(data);
        assertEquals(2, result.get().day);
    }

    @Test
    void shouldReturnEmpty_WhenListIsEmpty() {
        assertTrue(service.findDayWithSmallestSpread(List.of()).isEmpty());
    }

    @Test
    void shouldReturnTheDay_WhenListHasSingleEntry() {
        List<DailyWeather> data = List.of(new DailyWeather(1, 100.0, 90.0));
        assertEquals(1, service.findDayWithSmallestSpread(data).get().day);
    }

    @Test
    void shouldReturnFirstDay_WhenMultipleDaysHaveSameSpread() {
        List<DailyWeather> data = List.of(
                new DailyWeather(1, 20.0, 10.0),
                new DailyWeather(2, 30.0, 20.0)
        );
        assertEquals(1, service.findDayWithSmallestSpread(data).get().day);
    }
}