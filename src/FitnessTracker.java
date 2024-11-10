import java.util.Date;
import java.util.List;

public class FitnessTracker {
    private int userId;
    private List<Exercise> exercises;

    public FitnessTracker(int userId) {
        this.userId = userId;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }


}
