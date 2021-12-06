package Schedule.UseCase;
import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.Entities.*;
import Schedule.ScheduleDataAccess;
import java.time.DayOfWeek;

/**
 * A reminder of the day's activities for the user upon login.
 */

public class ReminderPromptUseCase {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    public ReminderPromptUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Returns the string representation of the reminder for the given day.
     * @param username username of the user
     * @param dayOfWeek day to remind user
     * @return a String representation of a User's day.
     */
    public String remind(String username, DayOfWeek dayOfWeek) {
        FetchSchedulesUseCase activeSchedule = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
        Schedule currentSchedule = activeSchedule.getActiveSchedule(username);
        if (currentSchedule == null) {
            outputBoundary.print("You don't have an active schedule yet :(");
            return null;
        }
        Day scheduledDay = currentSchedule.getDay(dayOfWeek); // assign this value to scheduled workout
        outputBoundary.print(scheduledDay.printDay(dayOfWeek.getValue()));
        return scheduledDay.printDay(dayOfWeek.getValue());
    }
}
