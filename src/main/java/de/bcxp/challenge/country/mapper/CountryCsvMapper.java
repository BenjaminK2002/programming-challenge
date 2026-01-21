package de.bcxp.challenge.country.mapper;

import de.bcxp.challenge.common.mapper.LineMapper;
import de.bcxp.challenge.country.model.Country;

public class CountryCsvMapper implements LineMapper<Country> {

    @Override
    public Country map(String line) {
        String[] parts = line.split(";");

        // Sicherheitscheck: Werte bis mindestens Index 4 (Area)
        if (parts.length < 5) {
            throw new IllegalArgumentException("Ungültige Länder-Zeile: " + line);
        }

        try {
            // Index 0 = Name
            String name = parts[0].trim();

            // Index 3 = Population
            // Entfernen von Punkten/Leerzeichen, falls Tausendertrennzeichen drin sind
            String popString = parts[3].replace(".", "").trim();
            long population = Long.parseLong(popString);

            // Index 4 = Area
            // Berreinigung von ./,
            String areaString = parts[4].replace(",", ".").trim();
            double area = Double.parseDouble(areaString);

            return new Country(name, population, area);

        } catch (NumberFormatException e) {
            throw new RuntimeException("Konnte Zahlen nicht parsen in Zeile: " + line, e);
        }
    }
}