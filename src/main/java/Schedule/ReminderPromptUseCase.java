package Schedule;
/**
 * A reminder of the day's activities for the user upon login.
 */

import java.time.DayOfWeek;

public class ReminderPromptUseCase {

    private final ScheduleDataAccess databaseInterface;

    public ReminderPromptUseCase(ScheduleDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    /**
     * @param username username of the user
     * @param dayOfWeek
     * @return a String representation of a User's day.
     */
    public String remind(String username, DayOfWeek dayOfWeek) {
        FetchSchedulesUseCase activeSchedule = new FetchSchedulesUseCase(databaseInterface);
        Schedule currentSchedule = activeSchedule.getActiveSchedule(username);
        Day scheduledDay = currentSchedule.getDay(dayOfWeek); // assign this value to scheduled workout
        // TODO: Let presenter handle this print statement
        System.out.println(scheduledDay.printDay(dayOfWeek.getValue()));
        return scheduledDay.printDay(dayOfWeek.getValue());
    }
}
