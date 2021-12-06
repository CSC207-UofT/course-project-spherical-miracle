package Schedule.Boundary;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public interface ScheduleOutputBoundary {
    void scheduleMadeMessage(String returnMessage);
    void scheduleInfoMessage(String name);
    void something(boolean signedUp);
    void listSchedules(List<String> schedules);
    void currentActiveSchedule(String scheduleName);
    void setActive(String scheduleName);
    boolean isPublic();
    void noActiveSchedule();
    void print(String s);
    void deleteSchedule(String user, String scheduleName);
    String viewEditDeleteActivateOptions();

    /**
     * Returns the number corresponding to the schedule that the user would like to view.
     * If they do not want to view, returns -1.
     * @return - number corresponding to the desired schedule
     * @param size
     */
    int chooseScheduleFromList(int size);
    int chooseWorkoutFromList(int size);

    int activeSchedulePrompt(int size);


    String selectEditOptions();
    DayOfWeek selectDay();

    String getName();
    void showNameChange(String oldName, String newName);
    Map<String, String> getNameAndCalories(String meal);

    /**
     * Displays the result of adding a workout
     * @param result - the ordinal value of the result
     *                 0 - SUCCESS
     *                 1 - TOO_MANY
     *                 2 - DUPLICATE_NAME
     * @param name Workout name
     */
    void showAddWorkoutResult(int result, String name);
    String createDayPrompt();

    String selectByName(List<String> names);
}
