package Domain.Schedule.UseCase;
import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Domain.Schedule.Entities.*;
import java.time.DayOfWeek;

/**
 * A reminder of the day's activities for the user upon login.
 */
public class ReminderPromptUseCase {

    private final ScheduleOutputBoundary outputBoundary;

    /**
     * Instantiate a use case that prompts a reminder from the active schedule.
     * @param outputBoundary output boundary for use case and presenter
     */
    public ReminderPromptUseCase(ScheduleOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Returns the string representation of the reminder for the given day.
     * @param activeSchedule the schedule to check for a reminder on
     * @param dayOfWeek day to remind user
     * @return a String representation of a User's day.
     */
    public String remind(DayOfWeek dayOfWeek, Schedule activeSchedule) {
        if (activeSchedule == null) {
            outputBoundary.print("You don't have an active schedule yet :(");
            return null;
        }
        Day scheduledDay = activeSchedule.getDay(dayOfWeek); // assign this value to scheduled workout
        outputBoundary.print(scheduledDay.printDay(dayOfWeek.getValue()));
        return scheduledDay.printDay(dayOfWeek.getValue());
    }
}
