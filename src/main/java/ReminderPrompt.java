import User.User;

public class ReminderPrompt {
    public static String remind(String username) {
        String date = (java.time.LocalDate.now()).toString();
        String scheduledWorkout = ""; // assign this value to scheduled workout
        if (scheduledWorkout.equals("Rest Day")) {
            return "Go to the gym you couch potato";
        } else {
            return "scheduledworkout";
        }
    }
}
