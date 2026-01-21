package de.bcxp.challenge;

import de.bcxp.challenge.weather.mapper.WeatherCsvMapper;
import de.bcxp.challenge.weather.model.DailyWeather;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherCsvMapperTest {

    private final WeatherCsvMapper mapper = new WeatherCsvMapper();

    @Test
    void shouldMapValidLine() {
        // Standardfall: Tag 1, Max 88, Min 59
        String line = "1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5";

        DailyWeather result = mapper.map(line);

        assertEquals(1, result.day);
        assertEquals(88.0, result.maxTemp);
        assertEquals(59.0, result.minTemp);
    }

    @Test
    void shouldHandleDirtyDataWithAsterisksAndSpaces() {
        // Simuliert "dreckige" Daten (z.B. "88*" oder " 59 ")
        String dirtyLine = " 2 , 88* , 59* , ...";

        DailyWeather result = mapper.map(dirtyLine);

        assertEquals(2, result.day);
        assertEquals(88.0, result.maxTemp, "Sollte Sternchen entfernen");
        assertEquals(59.0, result.minTemp, "Sollte Leerzeichen entfernen");
    }

    @Test
    void shouldThrowException_WhenLineIsTooShort() {
        // Zeile mit zu wenig Spalten
        String brokenLine = "1,88";

        assertThrows(IllegalArgumentException.class, () -> {
            mapper.map(brokenLine);
        });
    }

    @Test
    void shouldThrowException_WhenHeaderLineIsParsed() {
        // Wenn versehentlich die Header-Zeile geparst wird
        String header = "Day,MxT,MnT,AvT";

        assertThrows(NumberFormatException.class, () -> {
            mapper.map(header);
        });
    }
}