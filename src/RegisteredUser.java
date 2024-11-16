public class RegisteredUser extends User {
    private String email;  // unique 
    private String hashedPassword;
    private String goal;

    public RegisteredUser(String name, int age, char gender, int heightInches, int weightPounds, String email, String hashedPassword, String goal) {
        super(name, age, gender, heightInches, weightPounds);
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.goal = goal;
    }
    
    public boolean login(String email, String password) {
        return true;
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
	
	public String getGoal() {
		return goal;
	}
	
	public void setGoal(String goal) {
		this.goal = goal;
	}

}
