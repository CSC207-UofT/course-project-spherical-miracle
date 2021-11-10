package Schedule;

public class SetActiveScheduleUseCase {

    private ScheduleDataAccess scheduleDatabase;

    public SetActiveScheduleUseCase(ScheduleDataAccess database) {
        this.scheduleDatabase = database;
    }

    public void setAsActiveSchedule(String username, Schedule schedule) {
        scheduleDatabase.updateCurrentSchedule(username, schedule.getId());
    }
}
