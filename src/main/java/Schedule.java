public class Schedule {

    private String name;

    private Workout[] plan = new Workout[7];

    /**
     * Construct an Schedule, giving the name.
     * @param name
     */
    public Schedule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: Possibly track the exact hour of the workout
    /**
     * Add workout to the list of workout on the day
     * @param day the days of the week from 0 being Sunday to 6 being Saturday
     * @param workout
     */
    public void addWorkout(int day, Workout workout) {
        plan[day] = workout;
    }

    /**
     * Remove workout from the list of workout on the day
     * @param day
     *
     * Precondition: workout is in the list of workouts of the day
     */
    public void cancelWorkout(int day) {
        plan[day] = null;
    }

}