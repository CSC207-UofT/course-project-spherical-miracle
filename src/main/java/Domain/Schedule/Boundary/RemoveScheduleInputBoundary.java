package Domain.Schedule.Boundary;

import Domain.Schedule.Entities.Schedule;

public interface RemoveScheduleInputBoundary {
    /**
     * Removes the schedule with ID scheduleID from the user.
     * @param username - username of the User to remove the schedule from
     * @param schedule - schedule to be removed from User
     */
    void removeSchedule(String username, Schedule schedule);

    /**
     * Removes the schedule with ID scheduleID from the user without printing a message
     * @param username - username of the User to remove the schedule from
     * @param scheduleID - id of schedule to be removed from User
     */
    void removeWithoutOutput(String username, String scheduleID);
}
