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
    public void setDay(int date, Day day) {
        plan[date] = day;
    }

    /**
     * Remove the day from the list of days for a specific date.
     * @param date the specified day
     * Precondition: the given date exists in this current schedule
     */
    public void cancelDay(int date) { plan[date] = null; }

    /**
     * Return the workout plan for a specific day.
     * @param day the specific day
     */
    public Day getDay(int date) {
        return plan[date];
    }

    /**
     * Print a string representation of a user's specific schedule.
     */
    public String printSchedule(){
        String workout;
        String output_msg = "";
        for(int i=0; i <= 6; i++){
            if (this.getDay(i) == null) {
                workout = "Rest Day";
            } else {
                workout = this.getDay(i).getDay();
            }
            //String workout = sched.getWorkout(0).getName();
            output_msg += "This is your workout for day " + (i + 1) + ": " + workout + "\n";
        }
        return output_msg;
    }
}