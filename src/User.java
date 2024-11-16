
public class User {    
	protected String name;
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
    
    public String getName() { 
		return name; 
	}
	
    public void setName(String name) { 
    	this.name = name; 
    }

    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(int heightInches) {
		this.heightInches = heightInches;
	}

	public int getWeightPounds() {
		return weightPounds;
	}

	public void setWeightPounds(int weightPounds) {
		this.weightPounds = weightPounds;
	}

	
    
}
