package Schedule;

import java.util.ArrayList;
import java.util.List;

public class FetchSchedulesUseCase {
    private ScheduleDataAccess scheduleDatabase;

    public FetchSchedulesUseCase() {

    }
    public List<Schedule> getPublicSchedules() {
        ArrayList<Schedule> publicSchedules = new ArrayList<Schedule>();
        String[] schedules = scheduleDatabase.loadScheduleWithID("StringID");
        for (String scheduleString: schedules) {
           publicSchedules.add(toSchedule(scheduleString));
        }
        return publicSchedules;
    }

    private Schedule toSchedule(String details) {
       return new Schedule("123");
    }
}
