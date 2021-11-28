package Schedule;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import Schedule.Boundary.*;
import Schedule.UseCase.*;

/**
 * A controller that manages a schedule for a user.
 */

public class ScheduleController {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    /**
     * Construct a list of the information needed to create a new user and the UserDatabase data.
     * @param databaseInterface the dataAccessInterface to the database for schedules.
     */
    public ScheduleController(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary){
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;

    }

    public String createSchedule(String scheduleName, String username) {
        CreateScheduleUseCase c = new CreateScheduleUseCase(databaseInterface, outputBoundary);
        return c.createSchedule(scheduleName, username);
    }

    /**
     * Create a new schedule with the given name.
     * Otherwise, return false.
     **/
    public void createSchedule(String scheduleName, String username, boolean isPublic, List<List<List<Map<String, String>>>> days) {
        //TODO: validating inputs
        //boolean is_valid = ;
        //if (is_valid) {
        CreateScheduleInputBoundary c = new CreateScheduleUseCase(databaseInterface, outputBoundary);
        c.createSchedule(scheduleName, username, isPublic, days);
        //}
        //return false;
    }

    /**
     * Removes the schedule from a user's database of schedules.
     * @param name the name of the schedule being removed
     */
    public void removeSchedule(String name) {
        //TODO: validating inputs
    }

    public void viewListOfSchedule(String username){
//        InputBoundary InputBoundary = new (scheduleDatabase, scheduleOutputBoundary);
        FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
        fetch.getScheduleAssociatedWith(username);
    }

    /**
     * Displays the list of public schedules
     */
    public void viewPublicSchedules() {
        FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
        fetch.getPublicSchedules();
    }

    public void reminderFor(String username, DayOfWeek dayOfWeek) {
        ReminderPromptUseCase reminder = new ReminderPromptUseCase(databaseInterface, outputBoundary);
        reminder.remind(username, dayOfWeek);
    }

    public boolean checkDuplicateFor(String workoutName, DayOfWeek dayOfWeek, String scheduleID) {
        FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
        CheckDuplicateWorkoutUseCase checkDuplicate = new CheckDuplicateWorkoutUseCase(fetch);
        return checkDuplicate.checkDuplicateFor(workoutName, dayOfWeek, scheduleID);
    }
}
