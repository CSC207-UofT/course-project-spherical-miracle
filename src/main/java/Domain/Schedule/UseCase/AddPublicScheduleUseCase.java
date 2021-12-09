package Domain.Schedule.UseCase;

import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Database.ScheduleDataAccess;

/**
 * Adds a public schedule to a user's associated schedules.
 */
public class AddPublicScheduleUseCase {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    /**
     * Instantiate a use case that adds a schedule to the database
     * @param databaseInterface the access interface boundary between the database and the use case
     * @param outputBoundary output boundary for use case and presenter
     */
    public AddPublicScheduleUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary){
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * add a schedule into database, with their ID
     * @param username - the username of the user who has the schedule
     * @param scheduleID - the UUID of the schedule we want to view
     */
    public void addPublicSchedule(String username, String scheduleID){
        ScheduleDataAccess.ScheduleInfo scheduleInfo = databaseInterface.loadScheduleWithID(scheduleID);
        scheduleInfo.randomizeId();
        boolean isPublic = outputBoundary.isPublic();
        databaseInterface.createSchedule(scheduleInfo,username,isPublic);
    }
}
