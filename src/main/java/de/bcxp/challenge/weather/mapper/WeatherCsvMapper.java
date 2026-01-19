package de.bcxp.challenge.weather.mapper;

import de.bcxp.challenge.common.mapper.LineMapper;
import de.bcxp.challenge.weather.model.DailyWeather;

public class WeatherCsvMapper implements LineMapper<DailyWeather> {
    @Override
    public DailyWeather map(String line) {
        String[] parts = line.split(",");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Ungültige CSV-Zeile: Zu wenig Spalten.");
        }

        int day = Integer.parseInt(cleanValue(parts[0]));
        double maxT = Double.parseDouble(cleanValue(parts[1]));
        double minT = Double.parseDouble(cleanValue(parts[2]));

        return new DailyWeather(day, maxT, minT);
    }

    private String cleanValue(String value) {
        return value.replace("*", "").trim();
    }
}