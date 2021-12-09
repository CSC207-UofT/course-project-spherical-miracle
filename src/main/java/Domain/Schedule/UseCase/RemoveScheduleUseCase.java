package Domain.Schedule.UseCase;

import Domain.Schedule.Boundary.RemoveScheduleInputBoundary;
import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Database.ScheduleDataAccess;
import Domain.Schedule.Entities.Schedule;

/**
 * A use case which removes an associated schedule from a user
 */
public class RemoveScheduleUseCase implements RemoveScheduleInputBoundary {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    /**
     * Constructs a use case that allows for the removal of schedules.
     * @param databaseInterface - the database gateway
     * @param outputBoundary - the boundary for output
     */
    public RemoveScheduleUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void removeSchedule(String username, Schedule schedule) {
        removeWithoutOutput(username, schedule.getId());
        outputBoundary.deleteSchedule(username, schedule.getName());
    }

    @Override
    public void removeWithoutOutput(String username, String scheduleID) {
        databaseInterface.updateCurrentSchedule(username, "");
        databaseInterface.deleteUserSchedule(username, scheduleID);
    }
}
