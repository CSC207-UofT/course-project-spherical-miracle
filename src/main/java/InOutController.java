import User.*;
import Schedule.*;

/**
 * A controller that delegates tasks based on user commands that are input.
 */

public class InOutController {

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
}

// for testing purposes:
//    public static String login(){
//        return "hi";
//    }
