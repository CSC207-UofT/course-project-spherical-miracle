import User.*;
import Schedule.*;

/**
 * A controller that delegates tasks based on user commands that are input.
 */

public class InOutController {

    // signup method
    // return a message describing if the user was successfully signed up
    /**
     * Registers the user under a new account.
     *
     * @param username the desired username of the new user
     * @param password the desired password of the new user
     * @param name the name of the user
     * @param email the desired email of the new user
     * @param da the interface used to access the database
     * @return whether the user was successfully registered or not
     *
     */
    public static String register(String username, String password, String name, String email, UserDataAccess da) {
        UserController manageUser = new UserController(da);
        if (manageUser.addUser(username, password, name, email)) {

            return "Successfully signed up!";
        } else {
            return "Unsuccessful signup. Username is already taken.";
            // TODO: take care of any other reasons why they wouldn't be able to register
            // ex. invalid email, password strength
        }
    }

    /**
     * Exits out of the program.
     */
    public static void quit() {
        System.exit(0);
    }

    /**
     * Adds a new schedule to the database of schedules for a specific user.
     * @param sched the target schedule that is being added
     * @param schedDb the Workout.ScheduleDatabase corresponding to the specific user
     * @return a String reminder of the first workout in the schedule
     */
    // TODO: double check this description depending on how we connect users and schedules
    public static String finalizeSchedule(Schedule sched, ScheduleDatabase schedDb) {
        schedDb.addSchedule(sched);
        return sched.printSchedule();
    }

    /**
     * Creates a new workout in the desired day.
     * @param day the day that is being added to
     * @param workout the workout being added
     */
    public static void createWorkout(Day day, Workout workout){
        day.addWorkout(workout);
    }

    /**
     * Creates a new meal in the desired day.
     * @param day the day that is being added to
     * @param meal the meal being added
     */
    public static void createMeal(Day day, Meal meal){
        day.addMeal(meal);
    }

    /**
     * Merges two days together.
     * @param day the original day
     * @param day2 the day to put into the other day
     */
    public static Day mergeDay(Day day, Day day2){
        for (Meal meal : day2.getMeals()) {
            day.addMeal(meal);
        }
        for (Workout workout : day2.getWorkouts()) {
            boolean submit = day.addWorkout(workout);
            // remove this once the schedulerUI to do thing works
            if (!submit) {
                break;
            }
        }

        return day;
    }
}

// for testing purposes:
//    public static String login(){
//        return "hi";
//    }
