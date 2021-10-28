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
    // TODO: maybe change name to setWorkout, because User can replace workout
    // TODO: do we want to add multiple workouts, like one chest and one back.
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

    public Workout getWorkout(int day) {
        return plan[day];
    }

    public String printSchedule(){
        String Workout;
        String output_msg = "";
        for(int i=0; i <= 6; i++){
            if (this.getWorkout(i) == null) {
                Workout = "Rest Day";
            } else {
                Workout = this.getWorkout(i).getName();
            }
//        String Workout = sched.getWorkout(0).getName();
            output_msg += "This is your workout for day " + (i + 1) + ": " + Workout + "\n";
        }
        return output_msg;
    }

}