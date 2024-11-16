import java.util.Date;

public class AISuggestion {
    private int id;
    private int exerciseId;
    private Date date;
    private String suggestion;

    // Constructor to initialize the AISuggestion object
    public AISuggestion(int id, int exerciseId, Date date, String suggestion) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.date = date;
        this.suggestion = suggestion;
    }

    // Getters and setters for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and setters for userId
    public int getExerciseId() {
        return exerciseId;
    }

    public void setUserId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    // Getters and setters for date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Getters and setters for suggestion
    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
