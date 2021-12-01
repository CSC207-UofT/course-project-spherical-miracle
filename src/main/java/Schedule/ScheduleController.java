package Schedule;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import Schedule.Boundary.*;
import Schedule.UseCase.*;
import User.Entities.User;

/**
 * A controller that manages a schedule for a user.
 */

public class ScheduleController {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;
    private final FetchSchedulesUseCase fetch;

    /**
     * Construct a list of the information needed to create a new user and the UserDatabase data.
     * @param databaseInterface the dataAccessInterface to the database for schedules.
     */
    public ScheduleController(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary){
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
        fetch = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
    }

    public String createSchedule(String scheduleName, String username) {
        ManageScheduleUseCase c = new ManageScheduleUseCase(databaseInterface, outputBoundary);
        System.out.println("For each of the 7 days in your schedule, you can have up to five different workouts.");
        return c.createSchedule(scheduleName, username);
    }

    /**
     * Create a new schedule with the given name.
     * Otherwise, return false.
     **/
    public void createSchedule(String scheduleName, String username, boolean isPublic, List<List<List<Map<String, String>>>> days) {
        CreateScheduleInputBoundary c = new ManageScheduleUseCase(databaseInterface, outputBoundary);
        c.createSchedule(scheduleName, username, isPublic, days);
    }

    /**
     * Removes the schedule from a user's database of schedules.
     * @param username the username of who the schedule belongs to
     */
    public void removeSchedule(String username) {
        RemoveScheduleInputBoundary rsu = new RemoveScheduleUseCase(databaseInterface);
        rsu.removeScheduleFromUser(username);
    }

    public void viewListOfSchedule(String username){
        List<String> schedulesIDs = fetch.getScheduleAssociatedWith(username);
        selectAndDisplaySchedule(schedulesIDs);
    }

    /**
     * Displays the list of public schedules
     */
    public void viewPublicSchedules() {
        List<String> schedulesIDs = fetch.getPublicSchedules();
        selectAndDisplaySchedule(schedulesIDs);
    }

    public void reminderFor(String username, DayOfWeek dayOfWeek) {
        ReminderPromptUseCase reminder = new ReminderPromptUseCase(databaseInterface, outputBoundary);
        reminder.remind(username, dayOfWeek);
    }

    private void selectAndDisplaySchedule(List<String> schedulesIDs) {
        int index = outputBoundary.viewSpecificSchedule(schedulesIDs.size());
        if (schedulesIDs.size() == 0)
            return;
        if (index != -1) {
            DisplayScheduleUseCase display = new DisplayScheduleUseCase(outputBoundary);
            display.displaySchedule(fetch.getScheduleWithID(schedulesIDs.get(index)));
        }
    }
}
