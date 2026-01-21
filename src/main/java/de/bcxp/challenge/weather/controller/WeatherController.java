package de.bcxp.challenge.weather.controller;

import de.bcxp.challenge.common.repository.CsvFileRepository;
import de.bcxp.challenge.weather.model.DailyWeather;
import de.bcxp.challenge.weather.service.WeatherService;

import java.util.List;
import java.util.Optional;

public class WeatherController {
    private final CsvFileRepository<DailyWeather> repository;
    private final WeatherService service;

    public WeatherController(CsvFileRepository<DailyWeather> repository, WeatherService service) {
        this.repository = repository;
        this.service = service;
    }

    public void run() {
        System.out.println("--- Starte Wetter-Analyse ---");
        List<DailyWeather> data = repository.loadAll();
        Optional<DailyWeather> result = service.findDayWithSmallestSpread(data);
        if (result.isPresent()) {
            DailyWeather day = result.get();
            System.out.printf("Tag %d hat den kleinsten Spread: %.2f%n", day.day, day.getSpread());
        } else {
            System.out.println("Keine Wetterdaten gefunden.");
        }
    }
}