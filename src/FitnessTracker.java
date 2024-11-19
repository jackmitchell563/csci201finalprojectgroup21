import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FitnessTracker {
    private int userId;
    private List<Exercise> exercises;

    public FitnessTracker(int userId) {
        this.userId = userId;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(int index) {
        if (index >= 0 && index < exercises.size()) {
            exercises.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid exercise index.");
        }
    }

    public List<Exercise> getExercisesByDate(LocalDate date) {
        List<Exercise> result = new ArrayList<>();
        for (Exercise exercise : exercises) {
            if (exercise.getDate().equals(date)) {
                result.add(exercise);
            }
        }
        return result;
    }

    public double getTotalWeightLiftedByDate(LocalDate date) {
        double totalWeight = 0.0;
        for (Exercise exercise : getExercisesByDate(date)) {
            totalWeight += exercise.getWeight() * exercise.getRepetitions() * exercise.getSets();
        }
        return totalWeight;
    }

    public double getTotalExerciseTimeByDate(LocalDate date) {
        double totalTime = 0.0;
        for (Exercise exercise : getExercisesByDate(date)) {
            totalTime += exercise.getDuration();
        }
        return totalTime;
    }

    public List<Exercise> getAllExercises() {
        return new ArrayList<>(exercises);
    }

    public List<Exercise> getExercisesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Exercise> result = new ArrayList<>();
        for (Exercise exercise : exercises) {
            if ((exercise.getDate().isEqual(startDate) || exercise.getDate().isAfter(startDate)) &&
                (exercise.getDate().isEqual(endDate) || exercise.getDate().isBefore(endDate))) {
                result.add(exercise);
            }
        }
        return result;
    }

    public double getTotalWeightLifted(LocalDate startDate, LocalDate endDate) {
        double totalWeight = 0.0;
        for (Exercise exercise : getExercisesByDateRange(startDate, endDate)) {
            totalWeight += exercise.getWeight() * exercise.getRepetitions() * exercise.getSets();
        }
        return totalWeight;
    }

    public double getAverageDuration(LocalDate startDate, LocalDate endDate) {
        List<Exercise> filteredExercises = getExercisesByDateRange(startDate, endDate);
        if (filteredExercises.isEmpty()) {
            return 0.0;
        }
        double totalDuration = 0.0;
        for (Exercise exercise : filteredExercises) {
            totalDuration += exercise.getDuration();
        }
        return totalDuration / filteredExercises.size();
    }

    public void displayHistoricalData(LocalDate startDate, LocalDate endDate) {
        System.out.println("Historical Data for User ID: " + userId);
        System.out.println("From: " + startDate + " To: " + endDate);
        System.out.println("Total Weight Lifted: " + getTotalWeightLifted(startDate, endDate) + " kg");
        System.out.println("Average Exercise Duration: " + getAverageDuration(startDate, endDate) + " minutes");
        System.out.println("Details of Exercises:");
        for (Exercise exercise : getExercisesByDateRange(startDate, endDate)) {
            System.out.println(exercise);
        }
    }

    public void clearAllExercises() {
        exercises.clear();
    }
}
