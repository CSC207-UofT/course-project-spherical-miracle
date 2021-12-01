package Schedule.UseCase;

import Schedule.Boundary.RemoveScheduleInputBoundary;
import Schedule.Entities.Schedule;
import Schedule.ScheduleDataAccess;
import Schedule.ScheduleDatabase;

import java.util.List;

public class RemoveScheduleUseCase implements RemoveScheduleInputBoundary {

    private final ScheduleDataAccess scheduleDatabase;

    public RemoveScheduleUseCase(ScheduleDataAccess scheduleDatabase) {
        this.scheduleDatabase = scheduleDatabase;
    }
    @Override
    public void remove(String username, String scheduleName) {
        String uuid = "";
        List<ScheduleDataAccess.ScheduleInfo> schedList = scheduleDatabase.loadSchedulesAssociatedWith(username);
        for (ScheduleDataAccess.ScheduleInfo item: schedList){
            if (item.getName().equals(scheduleName)) {
                // gets id of first schedule it encounters with matching name
                // TODO: ask user if they want to delete which schedule if duplicate names?
                uuid = item.getId();
                break;
            }
        }
        scheduleDatabase.deleteSchedule(username, uuid);
    }
}
