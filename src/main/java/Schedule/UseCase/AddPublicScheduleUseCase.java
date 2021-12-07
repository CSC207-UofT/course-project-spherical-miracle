package Schedule.UseCase;

import Schedule.Boundary.ScheduleOutputBoundary;
import Database.ScheduleDataAccess;

public class AddPublicScheduleUseCase {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    public AddPublicScheduleUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary){
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    public void addPublicSchedule(String username, String scheduleID){
        ScheduleDataAccess.ScheduleInfo scheduleInfo = databaseInterface.loadScheduleWithID(scheduleID);
        scheduleInfo.randomizeId();
        boolean isPublic = outputBoundary.isPublic();
        databaseInterface.createSchedule(scheduleInfo,username,isPublic);

    }

}
