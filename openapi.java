package openapi;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class openapi {
   public static void main(String[] args) throws Exception {
       URL url = new URL("http://localhost:5000/process");
       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "application/json");
       conn.setDoOutput(true);
       Gson gson = new Gson();
       
       ArrayList<String> testList = new ArrayList<String>();
       testList.add("Monday: 5 sets of 10 50lb weighted squats");
       testList.add("Tuesday: 6 sets of 15 50lb weighted pull-ups");
       testList.add("Wednesday: 2 sets of 8 150lb chest flys");
       testList.add("Thursday: 10 sets of 20 100lb weighted calf raises");
       testList.add("Friday: 5 sets of 90 second deadhang");
       testList.add("Saturday: 12 sets of 12 100lb wrist curls");
       testList.add("Sunday: 9 sets of 12 200lb lat pulldowns");
       
       WorkoutObject testObject = new WorkoutObject(testList, "m", 68, 135, "climb v7"); // initialize WorkoutObject here
       
       String jsonInput = gson.toJson(testObject); // change passed object depending 
       System.out.println("jsonInput: " + jsonInput);
       String responseStr = "";
       try (OutputStream os = conn.getOutputStream()) {
           os.write(jsonInput.getBytes());
           os.flush();
       }
       System.out.println("Waiting for response...");
       try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
           String inputStr;
           while ((inputStr = reader.readLine()) != null)
               responseStr += inputStr;
       }
       System.out.println("Response: " + responseStr);
       WorkoutRecommendation response = gson.fromJson(responseStr, WorkoutRecommendation.class);
       System.out.println(response.getWorkoutRec());
   }
}

