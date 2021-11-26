package Schedule.UseCase;

import Schedule.Entities.Schedule;
import Schedule.ScheduleDataAccess;

public class SetActiveScheduleUseCase {

    private final ScheduleDataAccess databaseInterface;

    /**
     * Constructs a use case that sets the active schedule for a user.
     * @param databaseInterface
     */
    public SetActiveScheduleUseCase(ScheduleDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    /**
     * Sets the given Schedule to be the active schedule of the specified user
     * @param username - username of the given user
     * @param schedule - instance of the given Schedule 
     */
    public void setAsActiveSchedule(String username, Schedule schedule) {
        databaseInterface.updateCurrentSchedule(username, schedule.getId());
    }
}
