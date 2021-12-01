package Schedule.UseCase;

import Schedule.Boundary.RemoveScheduleInputBoundary;
import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.Entities.Schedule;
import Schedule.ScheduleDataAccess;
import Schedule.ScheduleDatabase;

import java.util.List;

public class RemoveScheduleUseCase implements RemoveScheduleInputBoundary {

    private final ScheduleDataAccess scheduleDatabase;
    private final ScheduleOutputBoundary outputBoundary = null;

    public RemoveScheduleUseCase(ScheduleDataAccess scheduleDatabase) {
        this.scheduleDatabase = scheduleDatabase;
    }

    @Override
    public void removeScheduleFromUser(String username) {
        String scheduleID = outputBoundary.deleteSchedule(username, username);
        scheduleDatabase.deleteSchedule(scheduleID);
    }
}
