import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DatabaseHandler {
	private Connection connection;
	private Statement st;
	private ResultSet rs;

	public DatabaseHandler() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/FitnessTracker?user=root&password=rootPassword");
			st = connection.createStatement();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveUser(RegisteredUser user) {
		try {
			String query = String.format("INSERT INTO Users (name, age, gender, height_inches, weight_pounds, email, hashed_password, goal) "
									   + "VALUES ('%s', '%d', '%c', '%d', '%d', '%s', '%s', '%s')", 
									     user.getName(), user.getAge(), user.getGender(), user.getHeightInches(), user.getWeightPounds(), user.getEmail(), 
									     user.getHashedPassword(), user.getGoal());
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public RegisteredUser getUserById(int id) {
		RegisteredUser user = null;
		String query = String.format("SELECT * FROM Users where id=%d", id);
		try {
			rs = st.executeQuery(query);
			if (rs.next()) {
	            String name = rs.getString("name");
	            int age = rs.getInt("age");
	            char gender = rs.getString("gender").charAt(0);
	            int heightInches = rs.getInt("height_inches");
	            int weightPounds = rs.getInt("weight_pounds");
	            String email = rs.getString("email");
	            String hashedPassword = rs.getString("hashed_password");
	            String goal = rs.getString("goal");
	            user = new RegisteredUser(name, age, gender, heightInches, weightPounds, email, hashedPassword, goal);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void saveExercise(int userId, Date date, String name, int repetitions, int sets, int durationMins) {
	    try {
	        String query = String.format("INSERT INTO Exercise (user_id, date, name, repetitions, sets, duration_mins) "
	                                   + "VALUES ('%d', '%tF', '%s', '%d', '%d', '%d')", 
	                                     userId, date, name, repetitions, sets, durationMins);
	        st.executeUpdate(query);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public List<Exercise> getExercisesByUserId(int userId) {
	    List<Exercise> exercises = new ArrayList<>();
	    String query = String.format("SELECT * FROM Exercise WHERE user_id=%d", userId);
	    try {
	        ResultSet rs = st.executeQuery(query);
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            Date date = rs.getDate("date");
	            String name = rs.getString("name");
	            int repetitions = rs.getInt("repetitions");
	            int sets = rs.getInt("sets");
	            int durationMins = rs.getInt("duration_mins");
	            int isAISuggestion = rs.getInt("is_AI_Suggestion");
	            Exercise exercise = new Exercise(id, userId, date, name, repetitions, sets, durationMins, isAISuggestion);
	            exercises.add(exercise);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return exercises;
	}

	
	public void closeHandler() {
		try {
			if (st != null)
				st.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
