import java.time.LocalDate;

public class Exercise {
    private int id;
    private int userId;
    private LocalDate date;
    private String type; // Changed 'name' to 'type'
    private double weight;
    private int repetitions;
    private int sets;
    private double durationMins;

    public Exercise(int id, int userId, LocalDate date, String type, double weight, int repetitions, int sets, double durationMins) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.type = type;
        this.weight = weight;
        this.repetitions = repetitions;
        this.sets = sets;
        this.durationMins = durationMins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getDuration() {
        return durationMins;
    }

    public void setDuration(double durationMins) {
        this.durationMins = durationMins;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", repetitions=" + repetitions +
                ", sets=" + sets +
                ", durationMins=" + durationMins +
                '}';
    }
}
