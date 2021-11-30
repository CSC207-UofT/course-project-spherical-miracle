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
    void currentActiveSchedule(String scheduleName);
    String setActive();
    boolean isPublic();
    void noActiveSchedule();
    void reminderPrompt(String s);

    /**
     * Returns the index of ID of the schedule that the user would like to view. If they do not want to view, return -1.
     * @return
     * @param size
     */
    int viewSpecificSchedule(int size);

    int activeSchedulePrompt(int size);


    String selectEditOrSave();
    DayOfWeek selectDay();

    Map<String, String> getNameAndCalories(String meal);

    /**
     * Displays the result of adding a workout
     * @param result - the ordinal value of the result
     *                 0 - SUCCESS
     *                 1 - TOO_MANY
     *                 2 - DUPLICATE_NAME
     * @param name
     */
    void showAddWorkoutResult(int result, String name);
    String createDayPrompt();
}
