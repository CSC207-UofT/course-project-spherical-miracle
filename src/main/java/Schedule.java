public class Schedule{

    private String name;

    private List<Workout>[] plan = new ArrayList<>[7];

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

    public String setName() {
        return name;
    }

    // TODO: Possibly track the exact hour of the workout
    /**
     * Add workout to the list of workout on the day
     * @param day
     * @param workout
     */
    public void addWorkout(int day, Workout workout) {
        plan[day].add(workout);
    }

    /**
     * Remove workout from the list of workout on the day
     * @param day
     * @param workout
     *
     * Precondition: workout is in the list of workouts of the day
     */
    public void cancelWorkout(int day, Workout workout) {
        plan[day].remove(workout);
    }

}