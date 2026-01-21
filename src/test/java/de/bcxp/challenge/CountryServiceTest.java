package de.bcxp.challenge;

import de.bcxp.challenge.country.model.Country;
import de.bcxp.challenge.country.service.CountryService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CountryServiceTest {

    private final CountryService service = new CountryService();

    @Test
    void shouldFindCountryWithHighestDensity_NormalCase() {
        // Malta: Klein, aber voll -> Hohe Dichte (10 / 1 = 10)
        Country malta = new Country("Malta", 10, 1.0);
        // Sweden: Groß, aber leer -> Kleine Dichte (100 / 50 = 2)
        Country sweden = new Country("Sweden", 100, 50.0);
        // Germany: Mittel (50 / 10 = 5)
        Country germany = new Country("Germany", 50, 10.0);

        List<Country> countries = List.of(sweden, malta, germany);

        Optional<Country> result = service.findCountryWithHighestDensity(countries);

        assertTrue(result.isPresent());
        assertEquals("Malta", result.get().name, "Malta sollte die höchste Dichte haben");
    }

    @Test
    void shouldHandleEmptyList() {
        Optional<Country> result = service.findCountryWithHighestDensity(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldHandleZeroAreaGracefully() {
        // Ein Land mit Fläche 0 sollte Dichte 0 haben und nicht gewinnen
        Country weirdCountry = new Country("NoLand", 1000, 0.0);
        Country normalCountry = new Country("RealLand", 100, 10.0);

        List<Country> countries = List.of(weirdCountry, normalCountry);

        Optional<Country> result = service.findCountryWithHighestDensity(countries);

        assertEquals("RealLand", result.get().name);
    }
}