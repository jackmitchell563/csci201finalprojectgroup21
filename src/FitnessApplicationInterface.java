public class FitnessApplicationInterface {
    private FitnessTracker tracker;
    private RegisteredUser registeredUser;

    public void createAccount(String name, String email, String password, int age, char gender, int height, int weight,String goal) {
        // Validate input
    	
    	// should also check if email is taken... 
    	// save to database? 
    	
    	 
        if (name == null || name.isEmpty()) { // if left empty 
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (email == null || email.isEmpty() || !email.contains("@")) { // 
            throw new IllegalArgumentException("Invalid email address.");
        }
        if (password == null || password.length() < 8) { // not sure what the rquirements are yet tho... double checl 
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        if (age <= 0) { // valid inputs for the age + basic information 
            throw new IllegalArgumentException("Age must be greater than 0.");
        }
        if (gender != 'M' && gender != 'F' && gender != 'O') {
            throw new IllegalArgumentException("Gender must be 'M', 'F', or 'O'.");
        }
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Height and weight must be positive numbers.");
        }

        // Hash the password using SecurityManager
        String hashedPassword = SecurityManager.hashPassword(password);

        // Create a new RegisteredUser instance
        this.registeredUser = new RegisteredUser(name, age, gender, height, weight, email, hashedPassword, goal);


        // Print confirmation message
        System.out.println("Account successfully created for: " + name);
    }

}
