package Schedule.Boundary;

public interface RemoveScheduleInputBoundary {
    /**
     * Removes the schedule with ID scheduleID from the user.
     * @param username - username of the User to remove the schedule from
     * @param scheduleID - ID of the schedule to remove
     */
    void removeSchedule(String username, String scheduleID);
}
