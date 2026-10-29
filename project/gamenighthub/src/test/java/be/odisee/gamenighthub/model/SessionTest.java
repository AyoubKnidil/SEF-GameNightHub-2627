package be.odisee.gamenighthub.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionTest {
    private Session sessie;
    private Player emma;
    private Player noah;

    @BeforeEach
    void setUp() {
        sessie = new Session(new Game("Catan", 3, 4, 90), LocalDate.of(2026, 11, 14), "Gent");
        emma = new Player("Emma", new User("Emma", "emma@example.com", "hash1"));
        noah = new Player("Noah", new User("Noah", "noah@example.com", "hash2"));
    }

    // Opdracht 1.1: voegSpelerToe

    @Test
    void voegSpelerToe_nieuweSpeler_komtInDeelnemers() {
        // Arrange: gebeurt al in setUp()

        // Act
        sessie.voegSpelerToe(emma);

        // Assert
        assertEquals(List.of(emma), sessie.getSpelers());
    }

    @Test
    void voegSpelerToe_tweeVerschillendeSpelers_komenBeideInDeelnemers() {
        sessie.voegSpelerToe(emma);
        sessie.voegSpelerToe(noah);

        assertEquals(List.of(emma, noah), sessie.getSpelers());
    }

    @Test
    void voegSpelerToe_zelfdeSpelerTweeKeer_staatSlechtsEenKeerInLijst() {
        sessie.voegSpelerToe(emma);
        sessie.voegSpelerToe(emma);

        assertEquals(1, sessie.getSpelers().size());
    }

    // Opdracht 1.2: registreerUitslag

    @Test
    void registreerUitslag_deelnemer_voegtScoreToe() {
        sessie.voegSpelerToe(emma);

        sessie.registreerUitslag(emma, 12, true);

        Score score = sessie.getScores().get(0);
        assertAll(
            () -> assertEquals(1, sessie.getScores().size()),
            () -> assertEquals(emma, score.getSpeler()),
            () -> assertEquals(12, score.getPunten()),
            () -> assertTrue(score.isGewonnen())
        );
    }

    @Test
    void registreerUitslag_tweeDeelnemers_voegtTweeScoresToe() {
        sessie.voegSpelerToe(emma);
        sessie.voegSpelerToe(noah);

        sessie.registreerUitslag(emma, 12, true);
        sessie.registreerUitslag(noah, 7, false);

        assertEquals(2, sessie.getScores().size());
    }

    @Test
    void registreerUitslag_geenDeelnemer_voegtGeenScoreToe() {
        Player lisa = new Player("Lisa", new User("Lisa", "lisa@example.com", "hash3"));

        sessie.registreerUitslag(lisa, 5, false);

        assertTrue(sessie.getScores().isEmpty());
    }
}
