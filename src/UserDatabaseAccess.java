import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantLock;
import com.google.gson.Gson;


public class UserDatabaseAccess {
	  private static final String URL = "jdbc:mysql://localhost:3306/fitnessdb";
	    private static final String DB_USER = "root"; // SQL database... will be working on this
	    private static final String DB_PASSWORD = "password";
	    
	    
	    private static final Gson gson = new Gson();
	    private static final ReentrantLock lock = new ReentrantLock();

    static class User {
        int id;
        String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void getUser(String uri, PrintWriter out) {
        lock.lock();
        try {
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: application/json");
            out.println();

            User user = new User(1, "John Doe");
            String jsonResponse = gson.toJson(user);
            out.println(jsonResponse);
        } catch (Exception e) {
            sendErrorResponse(out, 500, "Internal Server Error");
        } finally {
            lock.unlock();
        }
    }

    public static void createUser(BufferedReader in, PrintWriter out) {
        lock.lock();
        try {
            out.println("HTTP/1.1 201 Created");
            out.println();
        } catch (Exception e) {
            sendErrorResponse(out, 500, "Internal Server Error");
        } finally {
            lock.unlock();
        }
    }

    public static void updateUser(String uri, BufferedReader in, PrintWriter out) {
        lock.lock();
        try {
            out.println("HTTP/1.1 200 OK");
            out.println();
        } catch (Exception e) {
            sendErrorResponse(out, 500, "Internal Server Error");
        } finally {
            lock.unlock();
        }
    }

    public static void deleteUser(String uri, PrintWriter out) {
        lock.lock();
        try {
            out.println("HTTP/1.1 204 No Content");
            out.println();
        } catch (Exception e) {
            sendErrorResponse(out, 500, "Internal Server Error");
        } finally {
            lock.unlock();
        }
    }

    private static void sendErrorResponse(PrintWriter out, int statusCode, String message) {
        out.println("HTTP/1.1 " + statusCode + " " + message);
        out.println("Content-Type: text/plain");
        out.println();
        out.println(message);
    }
}