package be.odisee.gamenighthub.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Game game;
    private LocalDate datum;
    private String locatie;
    private List<Player> spelers;
    private List<Score> scores;

    public Session(Game game, LocalDate datum, String locatie) {
        this.game = game;
        this.datum = datum;
        this.locatie = locatie;
        this.spelers = new ArrayList<>();
        this.scores = new ArrayList<>();
    }

    public void voegSpelerToe(Player speler) {
        if (!spelers.contains(speler)) {
            spelers.add(speler);
        }
    }

    public void registreerUitslag(Player speler, int punten, boolean gewonnen) {
        if (spelers.contains(speler)) {
            scores.add(new Score(speler, punten, gewonnen));
        }
    }

    public Game getGame() {
        return game;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getLocatie() {
        return locatie;
    }

    public List<Player> getSpelers() {
        return spelers;
    }

    public List<Score> getScores() {
        return scores;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;
        return Objects.equals(game, session.game) && Objects.equals(datum, session.datum) && Objects.equals(locatie, session.locatie);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(game);
        result = 31 * result + Objects.hashCode(datum);
        result = 31 * result + Objects.hashCode(locatie);
        return result;
    }
}
