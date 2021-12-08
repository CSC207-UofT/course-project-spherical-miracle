package Domain.Schedule.UseCase;

import Domain.Schedule.Boundary.RemoveScheduleInputBoundary;
import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Database.ScheduleDataAccess;
import Domain.Schedule.Entities.Schedule;

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
    public void removeSchedule(String username, String scheduleID, Schedule schedule) {
        databaseInterface.updateCurrentSchedule(username, "");
        databaseInterface.deleteUserSchedule(username, scheduleID);
        outputBoundary.deleteSchedule(username, schedule.getName());
    }

    void editRemoveSchedule(String username, String scheduleID) {
        databaseInterface.updateCurrentSchedule(username, "");
        databaseInterface.deleteUserSchedule(username, scheduleID);
    }


}
