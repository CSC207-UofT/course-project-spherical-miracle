package Schedule.Boundary;

public interface RemoveScheduleInputBoundary {
    /**
     * Removes the schedule with ID scheduleID from the user.
     * @param username - username of the user
     * @param scheduleID - ID of the schedule to be removed
     */
    void removeSchedule(String username, String scheduleID);
}
