import java.io.BufferedReader;
import java.io.PrintWriter;

public class UserController {

	public static void processRequest(String httpM, String uri, BufferedReader in, PrintWriter out) {
	    if ("GET".equalsIgnoreCase(httpM)) {
	        UserDatabaseAccess.getUserById(uri, out); // fix the getUser as well....
	    } else if ("POST".equalsIgnoreCase(httpM)) {
	        UserDatabaseAccess.createUser(in, out);
	    } else if ("PUT".equalsIgnoreCase(httpM)) {
	        UserDatabaseAccess.updateUser(uri, in, out);
	    } else if ("DELETE".equalsIgnoreCase(httpM)) {
	        UserDatabaseAccess.deleteUser(uri, out);
	    } else {
	        sendErrorResponse(out, 400, "Invalid HTTP Method");
	    }
	}

	private static void sendErrorResponse(PrintWriter out, int statusCode, String message) {
	    out.println("HTTP/1.1 " + statusCode + " " + message);
	    out.println("Content-Type: text/plain");
	    out.println();
	    out.println(message);
	}


}
