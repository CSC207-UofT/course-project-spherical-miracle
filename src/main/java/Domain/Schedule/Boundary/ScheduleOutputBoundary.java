package Domain.Schedule.Boundary;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;


public interface ScheduleOutputBoundary {
    void scheduleInfoMessage(String name);
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
     * @return number corresponding to the desired schedule
     * @param size of the list of schedule IDs to choose from
     */
    int chooseScheduleFromList(int size);

    /**
     * Returns the number corresponding to the schedule that the user would like to view.
     * If they do not want to view any, returns -1.
     */
    String selectEditOptions();

    /**
     * Returns a day that a user has specified to edit.
     */
    DayOfWeek selectDay();

    /**
     * Returns the new name of a schedule being edited.
     */
    String getName();

    /**
     * Returns a message alerting the user of the name change of their schedule.
     */
    void showNameChange(String oldName, String newName);

    /**
     * Gets the name and calories of a workout or meal.
     */
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

    /**
     * Creates and displays a menu of actions to take for a day.
     */
    String createDayPrompt();

    /**
     * Gets the name of a schedule that the user wants to modify.
     */
    String selectByName(List<String> names);
}
