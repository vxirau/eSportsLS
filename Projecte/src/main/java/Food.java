public class Food {
    private String name;
    private int energetic_value;
    private double fat;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getEnergetic_value() {
        return energetic_value;
    }
    public void setEnergetic_value(int energetic_value) {
        this.energetic_value = energetic_value;
    }
    public double getFat() {
        return fat;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }

    @Override
    public String toString() {
        return "Food{" + "energetic_value=" + energetic_value +
                ", " + "fat=" + fat +
                '}';
    }
}
