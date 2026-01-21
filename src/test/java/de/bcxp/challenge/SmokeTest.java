package de.bcxp.challenge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Example JUnit 5 test case.
 */
class SmokeTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        //Konsole auf unseren Stream umleiten
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        //Konsole wieder auf Standard zurücksetzen
        System.setOut(originalOut);
    }

    @Test
    void shouldRunApplicationAndPrintCorrectResults() {
        App.main(new String[]{});

        String output = outputStreamCaptor.toString();

        // Prüfen auf Task 1 (Wetter)
        assertTrue(output.contains("Tag 14"), "Sollte Tag 14 als Ergebnis für Wetter finden");
        assertTrue(output.contains("Spread"), "Sollte das Wort 'Spread' ausgeben");

        // Prüfen auf Task 2 (Länder)
        assertTrue(output.contains("Malta"), "Sollte Malta als Land mit höchster Dichte finden");
        assertTrue(output.contains("Einwohner/km²"), "Sollte die Einheit ausgeben");
    }

}