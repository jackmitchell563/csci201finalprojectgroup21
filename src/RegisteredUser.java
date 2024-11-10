public class RegisteredUser extends User {
    private String email;
    private String hashedPassword;

    public RegisteredUser(String name, int age, char gender, int heightInches, int weightPounds, String email, String hashedPassword) {
        super(name, age, gender, heightInches, weightPounds);
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

 
    public boolean login(String email, String password) {
        return true;
    }

}
