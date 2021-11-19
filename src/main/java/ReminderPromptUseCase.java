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

    public static String remind(String username, ScheduleDataAccess databaseInterface) {
        LocalDate today = LocalDate.now();
        DayOfWeek currentDay = today.getDayOfWeek();
        FetchActiveScheduleUseCase activeSchedule = new FetchActiveScheduleUseCase(databaseInterface);
        Schedule currentSchedule = activeSchedule.getActiveSchedule(username);
        // getting specific day
        Day scheduledWorkout = currentSchedule.getDay(currentDay); // assign this value to scheduled workout

        // TODO: print reminder
        if (scheduledWorkout.equals("Rest Day")) {
            return "Go to the gym you couch potato";
        } else {
            return "here is your schedule for today";
        }
    }
}
