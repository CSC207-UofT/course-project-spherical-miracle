package Schedule.Boundary;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public interface ScheduleOutputBoundary {
    void scheduleMadeMessage(String returnMessage);
    void scheduleInfoMessage(String name);
    void something(boolean signedUp);
    void listSchedules(List<String> schedules);
    void deleteSchedule(String scheduleName);

    /**
     * Returns the index of ID of the schedule that the user would like to view. If they do not want to view, return -1.
     * @return
     * @param size
     */
    int viewSpecificSchedule(int size);
    void outputTooManyWorkout();

    String selectEditOrSave();
    DayOfWeek selectDay();

    Map<String, String> getNameAndCalories(String meal);

    String createDayPrompt();
}
