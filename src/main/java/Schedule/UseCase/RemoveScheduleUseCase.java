package Schedule.UseCase;

import Schedule.Boundary.RemoveScheduleInputBoundary;
import Schedule.ScheduleDataAccess;

public class RemoveScheduleUseCase implements RemoveScheduleInputBoundary {

    private final ScheduleDataAccess scheduleDatabase;

    public RemoveScheduleUseCase(ScheduleDataAccess scheduleDatabase) {
        this.scheduleDatabase = scheduleDatabase;
    }
    @Override
    public void removeScheduleFromUser(String scheduleID, String username) {
        //TODO: implement remove() in ScheduleDataAccess
    }
}
