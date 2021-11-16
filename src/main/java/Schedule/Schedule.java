package Schedule;

import java.util.UUID;

/**
 * A seven-day schedule for a user to customise.
 */
public class Schedule {

    /**
     * The name of the schedule
     */
    private String name;

    /**
     * The unique identifier of this schedule.
     */
    private UUID id;

    /**
     * An array with 7 slots that can be filled up with Workout.Workout objects.
     */
    private Day[] plan = new Day[7];

    /**
     * Construct a Workout.Schedule with the given name.
     * @param name the given name
     */
    public Schedule(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public Schedule(String name, String id) {
        this.name = name;
        this.id = UUID.fromString(id);
    }

    /**
     * Return the name of the schedule.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the unique identifier of this schedule.
     * @return
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Set the name of the schedule to be the given name.
     */
    public void setName(String name) {
        this.name = name;
    }

    // TODO: Possibly track the exact hour of the workout
    /**
     * Setting a given day into the schedule.
     * @param date the days of the week from 0 being Sunday to 6 being Saturday
     * @param day the day being added
     */
    public void setDay(int date, Day day) {
        plan[date - 1] = day;
    }

    /**
     * Remove the day from the list of days for a specific date.
     * @param date the specified day
     * Precondition: the given date exists in this current schedule
     */
    public void cancelDay(int date) { plan[date] = null; }

    /**
     * Return the workout plan for a specific day.
     * @param date the specific day
     */
    public Day getDay(int date) {
        return plan[date];
    }

    /**
     * Print a string representation of a user's specific schedule.
     */
    public String printSchedule(){
        String workout;
        String meal;
        String output_msg = "";
        for(int i=1; i <= 7; i++){
            if (this.getDay(i-1) == null) {
                workout = "Rest Day";
                meal = "No meals";
            } else {
                workout = this.getDay(i-1).getWorkoutString();
                meal = this.getDay(i-1).getMealString();
            }
            //String workout = sched.getWorkout(0).getName();
            output_msg += "This is your plan(s) for day " + (i) + ": \n Workouts: " + workout + "\n" +
                    " Meal: " + meal + "\n ";
        }
        return output_msg;
    }
}