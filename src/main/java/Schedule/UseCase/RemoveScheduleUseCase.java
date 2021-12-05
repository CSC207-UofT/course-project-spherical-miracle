package Schedule.UseCase;

import Schedule.Boundary.RemoveScheduleInputBoundary;
import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.ScheduleDataAccess;

public class RemoveScheduleUseCase implements RemoveScheduleInputBoundary {

    private final ScheduleDataAccess scheduleDatabase;
    private final ScheduleOutputBoundary outputBoundary;

    public RemoveScheduleUseCase(ScheduleDataAccess scheduleDatabase, ScheduleOutputBoundary outputBoundary) {
        this.scheduleDatabase = scheduleDatabase;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Removes schedule from database
     * @param username username of the User to remove the schedule from
     * @param scheduleID ID of Schedule to remove
     */
    @Override
    public void remove(String username, String scheduleID) {
        scheduleDatabase.updateCurrentSchedule(username, "");
        scheduleDatabase.deleteUserSchedule(username, scheduleID);
        outputBoundary.deleteSchedule(username, scheduleID);

}
}
