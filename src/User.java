
public class User {    protected String name;
    protected int age;
    protected char gender;
    protected int heightInches;
    protected int weightPounds;

    public User(String name, int age, char gender, int heightInches, int weightPounds) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.heightInches = heightInches;
        this.weightPounds = weightPounds;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
}
