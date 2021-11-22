package Schedule;

public class RemoveScheduleUseCase implements RemoveScheduleInputBoundary {

    private final ScheduleDataAccess scheduleDatabase;

    public RemoveScheduleUseCase(ScheduleDataAccess scheduleDatabase) {
        this.scheduleDatabase = scheduleDatabase;
    }
    @Override
    public void remove() {
        //TODO: implement remove() in ScheduleDataAccess
    }
}
