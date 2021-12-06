package Schedule.Boundary;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public interface ScheduleOutputBoundary {
    void scheduleInfoMessage(String name);
    void listSchedules(List<String> schedules);
    void currentActiveSchedule(String scheduleName);
    boolean isPublic();
    void noActiveSchedule();
    void reminderPrompt(String s);
    void deleteSchedule(String user, String scheduleName);
    String DetailDeleteActivateOption();
    /**
     * Returns the index of ID of the schedule that the user would like to view. If they do not want to view, return -1.
     * @return index of the desired schedule ID
     * @param size
     */
    int chooseScheduleFromList(int size);
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
