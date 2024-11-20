import java.io.BufferedReader;
import java.io.PrintWriter;

public class UserController {

	public static void processRequest(String httpMethod, String uri, BufferedReader in, PrintWriter out) {
		
		switch(httpMethod) {
		  case "GET":
              UserDatabaseAccess.getUser(uri, out);
              break;
          case "POST":
              UserDatabaseAccess.createUser(in, out);
              break;
          case "PUT":
              UserDatabaseAccess.updateUser(uri, in, out);
              break;
          case "DELETE":
              UserDatabaseAccess.deleteUser(uri, out);
              break;
          default:
              sendErrorResponse(out, 400, "Bad Request - Invalid HTTP Method");
      }
	}
	 private static void sendErrorResponse(PrintWriter out, int statusCode, String message) {
	        out.println("HTTP/1.1 " + statusCode + " " + message);
	        out.println("Content-Type: text/plain");
	        out.println();
	        out.println(message);
	    }

}
