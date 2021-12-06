package Schedule.UseCase;

import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.Entities.Schedule;
import Schedule.ScheduleDataAccess;

public class AddPublicScheduleUseCase {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    public AddPublicScheduleUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary){
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    public void addPublicSchedule(String username, String scheduleID){
        ScheduleDataAccess.ScheduleInfo scheduleInfo = databaseInterface.loadScheduleWith(scheduleID);
        scheduleInfo.randomizeId();
        boolean isPublic = outputBoundary.isPublic();
        databaseInterface.createSchedule(scheduleInfo,username,isPublic);

    }

}
