import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;
import com.google.gson.Gson;
import java.sql.Date;

public class ExerciseDatabaseAccess {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Gson gson = new Gson();

    public static void getExercisesByUserIdAndDate(String uri, PrintWriter out) {
        // Endpoint: /exercises/user/{userId}/date/{date}
        lock.lock();
        try {
            // Extract userId and date from the uri
            String[] parts = uri.split("/");
            int userId = Integer.parseInt(parts[2]);
            Date date = Date.valueOf(parts[4]);  // YYYY-MM-DD

            try (Connection connection = DatabaseHandler.getConnection()) {
                String query = "SELECT * FROM Exercise WHERE user_id = ? AND date = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, userId);
                    statement.setDate(2, date);
                    ResultSet resultSet = statement.executeQuery();

                    StringBuilder jsonResponse = new StringBuilder("[");
                    while (resultSet.next()) {
                        Exercise exercise = new Exercise(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getDate("date"),
                            resultSet.getString("name"),
                            resultSet.getInt("repetitions"),
                            resultSet.getInt("sets"),
                            resultSet.getInt("duration_mins"),
                            resultSet.getInt("is_ai_suggestion")
                        );
                        jsonResponse.append(gson.toJson(exercise)).append(",");
                    }
                    if (jsonResponse.length() > 1) {
                        jsonResponse.setLength(jsonResponse.length() - 1); // Remove trailing comma
                    }
                    jsonResponse.append("]");

                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: application/json");
                    out.println();
                    out.println(jsonResponse.toString());
                }
            } catch (SQLException e) {
                sendErrorResponse(out, 404, "Exercise not found");
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void getExercisesByUserId(String uri, PrintWriter out) {
        // Endpoint: /exercises/user/{userId}
        lock.lock();
        try {
            // Extract userId from the URI
            int userId = Integer.parseInt(uri.split("/")[2]);

            try (Connection connection = DatabaseHandler.getConnection()) {
                String query = "SELECT * FROM Exercise WHERE user_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, userId);
                    ResultSet resultSet = statement.executeQuery();

                    // Build JSON response
                    StringBuilder jsonResponse = new StringBuilder("[");
                    while (resultSet.next()) {
                        Exercise exercise = new Exercise(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getDate("date"),
                            resultSet.getString("name"),
                            resultSet.getInt("repetitions"),
                            resultSet.getInt("sets"),
                            resultSet.getInt("duration_mins"),
                            resultSet.getInt("is_ai_suggestion")
                        );
                        jsonResponse.append(gson.toJson(exercise)).append(",");
                    }
                    if (jsonResponse.length() > 1) {
                        jsonResponse.setLength(jsonResponse.length() - 1); // Remove trailing comma
                    }
                    jsonResponse.append("]");

                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: application/json");
                    out.println();
                    out.println(jsonResponse.toString());
                }
            } catch (SQLException e) {
                sendErrorResponse(out, 500, "Internal Server Error");
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void createExercise(String uri, BufferedReader in, PrintWriter out) {
        // Endpoint: /exercises
        lock.lock();
        try (Connection connection = DatabaseHandler.getConnection()) {
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                requestBody.append(line);
            }
            Exercise newExercise = gson.fromJson(requestBody.toString(), Exercise.class);

            String query = "INSERT INTO Exercise (user_id, date, name, repetitions, sets, duration_mins, is_ai_suggestion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, newExercise.getUserId());
                statement.setDate(2, newExercise.getDate());
                statement.setString(3, newExercise.getType());
                statement.setInt(4, newExercise.getRepetitions());
                statement.setInt(5, newExercise.getSets());
                statement.setInt(6, (int) newExercise.getDuration());
                statement.setBoolean(7, newExercise.getIsAISuggestion());
                statement.executeUpdate();

                out.println("HTTP/1.1 201 Created");
                out.println();
            }
        } catch (SQLException | IOException e) {
            sendErrorResponse(out, 500, "Internal Server Error");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void updateExercise(String uri, BufferedReader in, PrintWriter out) {
        // Endpoint: /exercises/{id}
        lock.lock();
        try (Connection connection = DatabaseHandler.getConnection()) {
            int exerciseId = Integer.parseInt(uri.split("/")[2]);
            StringBuilder requestbody = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                requestbody.append(line);
            }
            Exercise updatedExercise = gson.fromJson(requestbody.toString(), Exercise.class);

            String query = "UPDATE Exercise SET name = ?, repetitions = ?, sets = ?, duration_mins = ?, is_ai_suggestion = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, updatedExercise.getType());
                statement.setInt(2, updatedExercise.getRepetitions());
                statement.setInt(3, updatedExercise.getSets());
                statement.setInt(4, (int) updatedExercise.getDuration());
                statement.setBoolean(5, updatedExercise.getIsAISuggestion());
                statement.setInt(6, exerciseId);
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    out.println("HTTP/1.1 200 OK");
                    out.println();
                } else {
                    sendErrorResponse(out, 404, "Exercise not found");
                }
            }
        } catch (SQLException | IOException e) {
            sendErrorResponse(out, 500, "Internal Server Error");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void deleteExercise(String uri, PrintWriter out) {
        // Endpoint: /exercises/{id}
        lock.lock();
        try (Connection connection = DatabaseHandler.getConnection()) {
            int exerciseId = Integer.parseInt(uri.split("/")[2]);
            String query = "DELETE FROM Exercise WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, exerciseId);
                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    out.println("HTTP/1.1 204 No Content");
                    out.println();
                } else {
                    sendErrorResponse(out, 404, "Exercise not found");
                }
            }
        } catch (SQLException e) {
            sendErrorResponse(out, 500, "Internal Server Error");
            e.printStackTrace();
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
