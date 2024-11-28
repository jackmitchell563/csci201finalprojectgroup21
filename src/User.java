public class User {
    protected int ID;
    protected String email;
    protected String hashedPassword;
    protected int weightPounds;
    protected int heightInches;
    protected int age;
    protected String gender; // Changed to String to match `ResultSet.getString("gender")`
    protected String goal;

    // Constructor to match the `ResultSet` data retrieval
    public User(int ID, String email, String hashedPassword, int weightPounds, int heightInches, int age, String gender, String goal) {
        this.ID = ID;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.weightPounds = weightPounds;
        this.heightInches = heightInches;
        this.age = age;
        this.gender = gender;
        this.goal = goal;
    }

    // Getters and setters for all fields
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(int weightPounds) {
        this.weightPounds = weightPounds;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}

