package Schedule;
/**
 * A reminder of the day's activities for the user upon login.
 */

import java.time.DayOfWeek;

public class ReminderPromptUseCase {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    public ReminderPromptUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * @param username username of the user
     * @param dayOfWeek
     * @return a String representation of a User's day.
     */
    public String remind(String username, DayOfWeek dayOfWeek) {
        FetchSchedulesUseCase activeSchedule = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
        Schedule currentSchedule = activeSchedule.getActiveSchedule(username);
        Day scheduledDay = currentSchedule.getDay(dayOfWeek); // assign this value to scheduled workout
        // TODO: Let presenter handle this print statement
        System.out.println(scheduledDay.printDay(dayOfWeek.getValue()));
        return scheduledDay.printDay(dayOfWeek.getValue());
    }
}
