package Database;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ScheduleDataAccess {

    String workoutName = "workoutName";
    String calories = "calories";
    String mealName= "mealName";

    /**
     * Returns the details of the schedule with id scheduleID.
     * @param scheduleID - the id of the desired schedule
     * @return an instance of ScheduleInfo containing the details of the schedule
     */
    ScheduleInfo loadScheduleWithID(String scheduleID);

    /**
     * Returns a list of the details of schedules associated with the specified user.
     * @param username - the username of the user
     * @return list of ScheduleInfo, each containing the details of a schedule
     */
    List<ScheduleInfo> loadSchedulesFor(String username);

    /**
     * Returns the detail of the user's active schedule.
     * @param username - the username of the user
     * @return return a ScheduleInfo, containing the information of the active schedule.
     */
    ScheduleInfo loadActiveSchedule(String username);
    
    /**
     * Saves the given schedule into the database.
     * @param scheduleInfo - a class containing information of the schedule
     * @param username - the username associated with the given schedule
     * @param isPublic - whether the schedule is public
     */
    void createSchedule(ScheduleInfo scheduleInfo, String username, boolean isPublic);

    /**
     * Updates a user's schedule's status based on if it is currently an active schedule.
     * @param username user that the schedule being updated belongs to
     * @param scheduleId id of the schedule being updated
     */
    void updateCurrentSchedule(String username, String scheduleId);

    /**
     * Returns a list of the details of all public schedules.
     * @return list of ScheduleInfo, each containing the details of a schedule
     */
    List<ScheduleInfo> loadPublicSchedules();

    /**
     * Deletes the schedule from the user's list of schedule.
     * @param username user that the schedule being deleted belongs to
     * @param scheduleId id of the schedule being deleted
     */
    void deleteUserSchedule(String username, String scheduleId);

    /**
     * A class encapsulating the details of a schedule.
     */
    class ScheduleInfo {
        private final String name;
        private String id;
        private final List<List<List<Map<String, String>>>> details;

        /**
         * Constructs a ScheduleInfo object with the given information.
         * @param id - the id of the schedule
         * @param name - the name of the schedule
         * @param details - the details of the schedule
         */
        public ScheduleInfo(String id, String name, List<List<List<Map<String, String>>>> details) {
            this.id = id;
            this.name = name;
            this.details = details;
        }

        /**
         * Get the id of the scheduleInfo.
         * @return a String representation for the id of the schedule
         */
        public String getId() {
            return id;
        }

        /**
         * Randomise the id of the scheduleInfo.
         */
        public void randomizeId(){ id = UUID.randomUUID().toString();}

        /**
         * Get the name of the scheduleInfo.
         * @return a String of name for the schedule.
         */
        public String getName() {
            return name;
        }

        /**
         * Get the information of the days, contained in scheduleInfo
         * @return list of days containing a list Workout and Meal information in a Map, for each day.
         */
        public List<List<List<Map<String, String>>>> getDetails() {
            return details;
        }
    }
}
