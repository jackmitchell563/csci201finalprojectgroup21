package openapi;

import java.util.ArrayList;

public class WorkoutRecommendation {
	private ArrayList<String> workout_rec;
	public WorkoutRecommendation(ArrayList<String> workout_rec) {
		this.workout_rec = workout_rec;
	}
	public ArrayList<String> getWorkoutRec() {
		return this.workout_rec;
	}
}
