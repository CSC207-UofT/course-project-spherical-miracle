package Schedule;

import java.util.List;
import java.util.Map;

public class CreateScheduleUseCase implements CreateScheduleInputBoundary {

    private final ScheduleDataAccess databaseInterface;

    /**
     * Instantiate a use case that creates a schedule.
     * @param databaseInterface - the access interface boundary between the databaseInterface and the use case.
     */
    public CreateScheduleUseCase(ScheduleDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public void createSchedule(String name, String username, boolean isPublic, List<List<List<Map<String, String>>>> days) {
        Schedule schedule = new Schedule(name);
        databaseInterface.saveSchedule(schedule.getId(), name, username, isPublic, days);
    }
}
