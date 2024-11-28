package openapi;

import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

/**
 * This class encapsulates all the data related to a user's
 * exercise plan and physical attributes.
 */
public class UserData {

    // list to hold Exercise objects
    private List<Exercise> exercises;

    // user's age in years
    private int age;

    // user's gender represented as a single character
    private char gender;

    // user's height in inches
    private int height;

    // user's weight in pounds
    private int weight;

    // user's fitness goal as a String
    private String goal;
    
    // current date as a Date object
    private Date currDate;

    public UserData() {
        this.exercises = null;
        this.age = 0;
        this.gender = '\0';
        this.height = 0;
        this.weight = 0;
        this.goal = null;
        this.currDate = Date.valueOf(LocalDate.now());
    }

    public UserData(List<Exercise> exercises, int age, char gender, int height, int weight, String goal, Date currDate) {
        this.exercises = exercises;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
        this.currDate = currDate;
    }

    // getter for exercises
    public List<Exercise> getExercises() {
        return exercises;
    }

    // setter for exercises
    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    // getter for age
    public int getAge() {
        return age;
    }

    // setter for age
    public void setAge(int age) {
        this.age = age;
    }

    // getter for gender
    public char getGender() {
        return gender;
    }

    // setter for gender
    public void setGender(char gender) {
        this.gender = gender;
    }

    // getter for height
    public int getHeight() {
        return height;
    }

    // setter for height
    public void setHeight(int height) {
        this.height = height;
    }

    // getter for weight
    public int getWeight() {
        return weight;
    }

    // setter for weight
    public void setWeight(int weight) {
        this.weight = weight;
    }

    // getter for goal
    public String getGoal() {
        return goal;
    }

    // setter for goal
    public void setGoal(String goal) {
        this.goal = goal;
    }
    
    // getter for currDate
    public Date getDate() {
        return currDate;
    }

    // setter for currDate
    public void getDate(Date currDate) {
        this.currDate = currDate;
    }

}
