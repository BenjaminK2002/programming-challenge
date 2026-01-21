package de.bcxp.challenge.common.mapper;
/**
 * Strategie-Interface zum Umwandeln einer CSV-Textzeile in ein Domain-Objekt.
 *
 * @param <T> Der Typ des Zielobjekts (z.B. DailyWeather oder Country)
 */
public interface LineMapper<T> {
    /**
     * Wandelt eine Roh-Zeile in ein Objekt um.
     *
     * @param line Die rohe CSV-Zeile
     * @return Das geparste Objekt
     * @throws RuntimeException wenn das Format ungültig ist
     */
    T map(String line);
}