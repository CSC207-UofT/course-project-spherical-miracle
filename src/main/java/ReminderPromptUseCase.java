/**
 * A reminder of the day's activities for the user upon login.
 */
import Schedule.Day;
import Schedule.FetchActiveScheduleUseCase;
import Schedule.Schedule;
import Schedule.ScheduleDataAccess;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReminderPromptUseCase {

    public ReminderPromptUseCase() {
    }

    /**
     * @param username username of the user
     * @param databaseInterface
     * @return a String representation of a User's day.
     */
    public static String remind(String username, ScheduleDataAccess databaseInterface) {
        LocalDate today = LocalDate.now();
        DayOfWeek currentDay = today.getDayOfWeek();
        FetchActiveScheduleUseCase activeSchedule = new FetchActiveScheduleUseCase(databaseInterface);
        Schedule currentSchedule = activeSchedule.getActiveSchedule(username);
        // getting specific day
        Day scheduledDay = currentSchedule.getDay(currentDay); // assign this value to scheduled workout
        return scheduledDay.printDay(currentDay.getValue());
    }
}
