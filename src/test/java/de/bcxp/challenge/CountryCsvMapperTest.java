package de.bcxp.challenge;

import de.bcxp.challenge.country.mapper.CountryCsvMapper;
import de.bcxp.challenge.country.model.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryCsvMapperTest {

    private final CountryCsvMapper mapper = new CountryCsvMapper();

    @Test
    void shouldMapValidLineWithSemicolon() {
        String line = "Austria;Vienna;1995;8926000;83855;447718;0.922;19";

        Country country = mapper.map(line);

        assertEquals("Austria", country.name);
        assertEquals(8_926_000, country.population);
        assertEquals(83_855.0, country.area);
    }

    @Test
    void shouldHandleNumberFormatting() {
        // Testet, ob Punkte in Zahlen (Tausendertrennzeichen) entfernt werden und ob Leerzeichen ignoriert werden
        String line = " TestLand ; Capital ; 2020 ; 1.000 ; 50,5 ; ... ";

        Country country = mapper.map(line);

        assertEquals("TestLand", country.name);
        assertEquals(1000, country.population, "Punkte in Population sollten entfernt werden");
        assertEquals(50.5, country.area, 0.01);
    }

    @Test
    void shouldThrowException_WhenLineIsTooShort() {
        String brokenLine = "Nur;Drei;Spalten";

        assertThrows(IllegalArgumentException.class, () -> {
            mapper.map(brokenLine);
        });
    }

    @Test
    void shouldThrowException_WhenNumbersAreGarbage() {
        // Text statt Zahl in der Einwohner-Spalte
        String badData = "Name;Cap;Year;KeineZahl;100;...";

        assertThrows(RuntimeException.class, () -> {
            mapper.map(badData);
        });
    }
}