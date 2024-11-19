import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
	        String hashedInput = hashPassword(inputPassword);
	        return hashedPassword.equals(hashedInput);
	    }
	 }

