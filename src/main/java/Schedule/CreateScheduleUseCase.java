package Schedule;

import java.util.ArrayList;

public class CreateScheduleUseCase implements CreateScheduleInputBoundary {

    private final ScheduleDataAccess database;

    public CreateScheduleUseCase(ScheduleDataAccess database) {
        this.database = database;
    }

    @Override
    public void createSchedule(String scheduleName, String username, boolean isPublic) {
        Schedule schedule = new Schedule(scheduleName);
        database.saveSchedule(schedule.getId(), scheduleName, username, isPublic, new ArrayList<>());
    }
}
