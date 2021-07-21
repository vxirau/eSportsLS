import java.util.List;

public class Jugador {

    private String name;
    private String main_position;
    private List<Champion> main_champion;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMain_position() {
        return main_position;
    }
    public void setMain_position(String main_position) {
        this.main_position = main_position;
    }
    public List<Champion> getMain_champion() {
        return main_champion;
    }
    public void setMain_champion(List<Champion> main_champion) {
        this.main_champion = main_champion;
    }
}
