public class InOut {

    // signup method
    // return a message describing if the user was successfully signed up
    public static String register(String username, String password, String name, String email, UserDatabase userDatabase) {
        ManageUser manageuser = new ManageUser(userDatabase);
        if (manageuser.addUser(username, password, name, email)) {
            return "Successfully signed up!";
        } else {
            return "Unsuccessful signup. Username is already taken.";
        }
    }

    //quit method
    public static void quit() {
        System.exit(0);
    }

    // adds a workout to the database of schedules and gives back a String reminder of the first workout in the schedule.
    public static String finalizeSchedule(Schedule sched, ScheduleDatabase schedDb) {
        schedDb.addSchedule(sched);
        if (sched.getWorkout(0) == null) {
            String fWorkout = "Rest Day";
        } else {
            String fWorkout = sched.getWorkout(0).getName();
        }
        String fWorkout = sched.getWorkout(0).getName();
        return "This is your workout for day 0:" + fWorkout;
    }

    public static void createWorkout(Schedule sched, String type, int day, int cal){
        sched.addWorkout(day, new Workout(type, cal));
    }
}
