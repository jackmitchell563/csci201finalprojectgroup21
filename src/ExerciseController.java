import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ExerciseController {

    public static void processRequest(String httpMethod, String uri, BufferedReader in, PrintWriter out) throws IOException {
        switch (httpMethod) {
            case "GET":
                if (uri.matches("/exercises/user/\\d+/date/\\d{4}-\\d{2}-\\d{2}")) {
                    // Matches /exercises/user/{userId}/date/{date}
                    ExerciseDatabaseAccess.getExercisesByUserIdAndDate(uri, out);
                } 
                else if (uri.matches("/exercises/user/\\d+")) {
                    // Matches /exercises/user/{userId}
                    ExerciseDatabaseAccess.getExercisesByUserId(uri, out);
                } 
                else {
                    sendErrorResponse(out, 404, "Resource not found");
                }
                break;
                
            case "POST":
                if (uri.equals("/exercises")) {
                    // Matches /exercises
                    ExerciseDatabaseAccess.createExercise(uri, in, out);
                } 
                else {
                    sendErrorResponse(out, 404, "Resource not found");
                }
                break;
                
            case "PUT":
                if (uri.matches("/exercises/\\d+")) {
                    // Matches /exercises/{id}
                    ExerciseDatabaseAccess.updateExercise(uri, in, out);
                } 
                else {
                    sendErrorResponse(out, 404, "Resource not found");
                }
                break;
                
            case "DELETE":
                if (uri.matches("/exercises/\\d+")) {
                    // Matches /exercises/{id}
                    ExerciseDatabaseAccess.deleteExercise(uri, out);
                } 
                else {
                    sendErrorResponse(out, 404, "Resource not found");
                }
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