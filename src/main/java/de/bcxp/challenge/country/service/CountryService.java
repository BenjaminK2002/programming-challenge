package de.bcxp.challenge.country.service;

import de.bcxp.challenge.country.model.Country;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CountryService {

    public Optional<Country> findCountryWithHighestDensity(List<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            return Optional.empty();
        }

        // Suche nach dem Max
        return countries.stream()
                .max(Comparator.comparingDouble(Country::getPopulationDensity));
    }
}