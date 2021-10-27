/**
 * A seven-day schedule for a user to customise.
 */
public class Schedule {

    /**
     * The name of the schedule
     */
    private String name;

    /**
     * An array with 7 slots that can be filled up with Workout objects.
     */
    private Workout[] plan = new Workout[7];

    /**
     * Construct an Schedule with the given name.
     * @param name the given name
     */
    public Schedule(String name) {
        this.name = name;
    }

    /**
     * Return the name of the schedule.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the schedule to be the given name.
     */
    public void setName(String name) {
        this.name = name;
    }

    // TODO: Possibly track the exact hour of the workout
    /**
     * Add the workout to the list of workouts for a specific day.
     * @param day the days of the week from 0 being Sunday to 6 being Saturday
     * @param workout the workout being added
     */
    // TODO: maybe change name to setWorkout, because User can replace workout
    // TODO: do we want to add multiple workouts, like one chest and one back.
    public void addWorkout(int day, Workout workout) {
        plan[day] = workout;
    }

    /**
     * Remove the workout from the list of workout for a specific day.
     * @param day the specified day
     * Precondition: workout is in the list of workouts of the day
     */
    public void cancelWorkout(int day) {
        plan[day] = null;
    }

    /**
     * Return the workout plan for a specific day.
     * @param day the specific day
     */
    public Workout getWorkout(int day) {
        return plan[day];
    }

    /**
     * Print a string representation of a user's specific schedule.
     */
    public String printSchedule(){
        String Workout;
        String output_msg = "";
        for(int i=0; i <= 6; i++){
            if (this.getWorkout(i) == null) {
                Workout = "Rest Day";
            } else {
                Workout = this.getWorkout(i).getName();
            }
            //String Workout = sched.getWorkout(0).getName();
            output_msg += "This is your workout for day " + (i + 1) + ": " + Workout + "\n";
        }
        return output_msg;
    }
}