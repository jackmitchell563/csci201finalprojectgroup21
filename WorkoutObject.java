package openapi;

import java.util.ArrayList;

public class WorkoutObject {
    private ArrayList<String> schedule;
    private String sex;
    private double height;
    private double weight;
    private String goal;
    public WorkoutObject(ArrayList<String> schedule, String sex, double height, double weight, String goal) {
    	this.schedule = schedule; this.sex = sex; this.height = height; this.weight = weight; this.goal = goal;
    }
    public ArrayList<String> getSchedule() {
    	return this.schedule;
    }
    public String getSex() {
    	return this.sex;
    }
    public double getHeight() {
    	return this.height;
    }
    public double getWeight() {
    	return this.weight;
    }
    public String getGoal() {
    	return this.goal;
    }
}
