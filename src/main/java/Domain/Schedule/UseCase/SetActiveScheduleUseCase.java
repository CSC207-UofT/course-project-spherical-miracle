package Domain.Schedule.UseCase;

import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Domain.Schedule.Entities.Schedule;
import Database.ScheduleDataAccess;

/**
 * A use case which selects a schedule from user's list and make it active
 */
public class SetActiveScheduleUseCase {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary scheduleOutputBoundary;

    /**
     * Constructs a use case that sets the active schedule for a user.
     * @param databaseInterface interface for database
     */
    public SetActiveScheduleUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary scheduleOutputBoundary) {
        this.databaseInterface = databaseInterface;
        this.scheduleOutputBoundary = scheduleOutputBoundary;
    }

    /**
     * Sets the given Schedule to be the active schedule of the specified user
     * @param username - username of the given user
     * @param schedule - instance of the given Schedule 
     */
    public void setAsActiveSchedule(String username, Schedule schedule) {
        databaseInterface.updateCurrentSchedule(username, schedule.getId());
        scheduleOutputBoundary.setActive(schedule.getName());
    }
}
