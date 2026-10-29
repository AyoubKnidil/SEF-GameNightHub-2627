package be.odisee.gamenighthub.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class GameTest {

    // Opdracht 1.3: Game

    @Test
    void constructor_geldigeGegevens_bewaartAlleGegevens() {
        Game game = new Game("Catan", 3, 4, 90);

        assertAll(
            () -> assertEquals("Catan", game.getTitel()),
            () -> assertEquals(3, game.getMinSpelers()),
            () -> assertEquals(4, game.getMaxSpelers()),
            () -> assertEquals(90, game.getGemiddeldeSpeelduur())
        );
    }

    @Test
    void equals_zelfdeTitelAndereGegevens_geeftTrue() {
        Game a = new Game("Catan", 3, 4, 90);
        Game b = new Game("Catan", 2, 6, 60);

        assertEquals(a, b);
    }

    @Test
    void equals_andereTitel_geeftFalse() {
        Game catan = new Game("Catan", 3, 4, 90);
        Game dobble = new Game("Dobble", 2, 8, 15);

        assertNotEquals(catan, dobble);
    }

    @Test
    void equals_andereHoofdletters_geeftFalse() {
        Game klein = new Game("Catan", 3, 4, 90);
        Game groot = new Game("CATAN", 3, 4, 90);

        assertNotEquals(klein, groot);
    }

    @Test
    void hashCode_gelijkeSpellen_geeftZelfdeHashCode() {
        Game a = new Game("Catan", 3, 4, 90);
        Game b = new Game("Catan", 2, 6, 60);

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void equals_null_geeftFalse() {
        Game game = new Game("Catan", 3, 4, 90);

        assertFalse(game.equals(null));
    }

    @Test
    void equals_andereKlasse_geeftFalse() {
        Game game = new Game("Catan", 3, 4, 90);

        assertFalse(game.equals("Catan"));
    }
}
