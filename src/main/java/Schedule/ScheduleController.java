package Schedule;

/**
 * A controller that manages a schedule for a user.
 */

public class ScheduleController {

    private final ScheduleDataAccess scheduleDatabase;

    /**
     * Construct a list of the information needed to create a new user and the User.UserDatabase data.
     * @param scheduleDatabase the dataAccessInterface to the database for schedules.
     */
    public ScheduleController(ScheduleDataAccess scheduleDatabase){
        this.scheduleDatabase= scheduleDatabase;
    }

    /**
     * Create a new schedule with the given name.
     * Otherwise, return false.
     **/
    public void createSchedule(String scheduleName, String username, boolean isPublic) {
        //TODO: validating inputs
        //boolean is_valid = ;
        //if (is_valid) {
            scheduleDatabase.saveSchedule(scheduleName, username, isPublic);
            //return true;
        //}
        //return false;
    }

    /**
     * Removes the schedule from a user's database of schedules.
     * @param name the name of the schedule being removed
     */
    public void removeSchedule(String name) {
        //TODO: validating inputs

    }
}
