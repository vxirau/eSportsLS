import java.util.List;

public class Equip {

    private String team;
    private String nationality;
    private double winrate;
    private List<Jugador> players;

    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public double getWinrate() {
        return winrate;
    }
    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }
    public List<Jugador> getPlayers() {
        return players;
    }
    public void setPlayers(List<Jugador> players) {
        this.players = players;
    }


    @Override
    public String toString() {
        return team + "[" + nationality + "] - " + winrate;
    }
}
