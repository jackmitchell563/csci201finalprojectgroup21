import java.security.MessageDigest;

public class SecurityManager {
	 public static String hashPassword(String password) {
	        try {
	         
	            MessageDigest md = MessageDigest.getInstance("SHA-256");

	         
	            byte[] hashedBytes = md.digest(password.getBytes());

	           
	            return Base64.getEncoder().encodeToString(hashedBytes);
	        } catch (NoSuchAlgorithmException e) {
	          
	            throw new RuntimeException("Error: SHA-256 algorithm not found.", e);
	        }
	    }

	 public boolean verifyPassword(String hashedPassword, String inputPassword) {
	        // Hash the input password and compare with the stored hash
	        String hashedInput = hashPassword(inputPassword);
	        return hashedPassword.equals(hashedInput);
	    }}
}
