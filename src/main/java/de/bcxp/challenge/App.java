package de.bcxp.challenge;

import de.bcxp.challenge.country.controller.CountryController;
import de.bcxp.challenge.common.repository.CsvFileRepository;
import de.bcxp.challenge.country.mapper.CountryCsvMapper;
import de.bcxp.challenge.country.model.Country;
import de.bcxp.challenge.country.service.CountryService;
import de.bcxp.challenge.weather.controller.WeatherController;
import de.bcxp.challenge.weather.mapper.WeatherCsvMapper;
import de.bcxp.challenge.weather.model.DailyWeather;
import de.bcxp.challenge.weather.service.WeatherService;

import java.nio.file.Path;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        Path weatherCsvPath = Path.of("src/main/resources/de/bcxp/challenge/weather.csv");
        WeatherCsvMapper weatherMapper = new WeatherCsvMapper();
        CsvFileRepository<DailyWeather> weatherCsvFileRepository = new CsvFileRepository<>(weatherCsvPath, weatherMapper);
        WeatherService weatherService = new WeatherService();
        WeatherController weatherController = new WeatherController(weatherCsvFileRepository, weatherService);
        weatherController.run();

        System.out.println(); // Leerzeile

        // --- TASK 2: LÄNDER ---
        Path countriesPath = Path.of("src/main/resources/de/bcxp/challenge/countries.csv");
        CountryCsvMapper countryMapper = new CountryCsvMapper();
        // Das GLEICHE Repo-Klasse, aber jetzt für Länder konfiguriert!
        CsvFileRepository<Country> countryRepo = new CsvFileRepository<>(countriesPath, countryMapper);
        CountryService countryService = new CountryService();
        CountryController countryApp = new CountryController(countryRepo, countryService);

        countryApp.run();
            }
    }
