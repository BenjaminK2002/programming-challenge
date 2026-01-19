package de.bcxp.challenge.weather.service;

import de.bcxp.challenge.weather.model.DailyWeather;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WeatherService {

    public Optional<DailyWeather> findDayWithSmallestSpread(List<DailyWeather> weatherData) {
        if (weatherData == null || weatherData.isEmpty()) {
            return Optional.empty();
        }

        return weatherData.stream()
                .min(Comparator.comparingDouble(DailyWeather::getSpread));
    }
}