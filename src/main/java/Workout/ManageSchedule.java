package Workout;

/**
 * A controller that manages a schedule for a user.
 */

public class ManageSchedule {

    /**
     * The name of the schedule.
     */
    private String name;

    /**
     * A HashMap of schedule names and Workout.Schedule objects.
     */
    private ScheduleDatabase manager;


    /**
     * Construct a list of the information needed to create a new user and the User.UserDatabase data.
     * @param name string of the schedule name.
     * @param manager Workout.ScheduleDatabase object
     */
    public ManageSchedule(String name, ScheduleDatabase manager){
        this.manager = manager;
        this.name = name;
    }

    /**
     * If the information is valid, add the user to the User.UserDatabase object then return true.
     * Otherwise, return false.
     **/
    public void AddSchedule() {
        //TODO: validating inputs
        //boolean is_valid = ;
        //if (is_valid) {
            Schedule new_Schedule = new Schedule(name);
            manager.getScheduleMap().put(name, new_Schedule);
            //return true;
        //}
        //return false;
    }

    /**
     * Removes the schedule from a user's database of schedules.
     * @param name the name of the schedule being removed
     */
    public void RemoveSchedule(String name) {
        //TODO: validating inputs
        manager.getScheduleMap().remove(name);
    }
}
