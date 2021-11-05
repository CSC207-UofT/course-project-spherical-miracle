package Schedule;

public class CreateScheduleUseCase implements CreateScheduleInputBoundary {

    private final ScheduleDataAccess database;

    public CreateScheduleUseCase(ScheduleDataAccess database) {
        this.database = database;
    }

    @Override
    public void createSchedule(String name, boolean isPublic) {
        database.saveSchedule(name, isPublic);
    }
}
