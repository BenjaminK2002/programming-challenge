package de.bcxp.challenge.country.controller;

import de.bcxp.challenge.common.exception.DataReadingException;
import de.bcxp.challenge.common.repository.CsvFileRepository;
import de.bcxp.challenge.country.model.Country;
import de.bcxp.challenge.country.service.CountryService;

import java.util.List;
import java.util.Optional;

public class CountryController {

    private final CsvFileRepository<Country> repository;
    private final CountryService service;

    public CountryController(CsvFileRepository<Country> repository, CountryService service) {
        this.repository = repository;
        this.service = service;
    }

    public void run() {
        System.out.println("--- Starte Länder-Analyse ---");
        try {
            List<Country> countries = repository.loadAll();
            Optional<Country> result = service.findCountryWithHighestDensity(countries);

            if (result.isPresent()) {
                Country c = result.get();
                System.out.printf("Land mit höchster Dichte: %s (%.2f Einwohner/km²)%n",
                        c.name, c.getPopulationDensity());
            } else {
                System.out.println("Keine Länderdaten gefunden.");
            }
        } catch (DataReadingException e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }
}