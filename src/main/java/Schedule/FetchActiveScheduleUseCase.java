package Schedule;

import Schedule.ScheduleDataAccess;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

/**
 * A use case that fetches the one active Schedule from the database.
 */

public class FetchActiveScheduleUseCase{
    private ScheduleDataAccess databaseInterface;

    /**
     * Constructs a use case that can fetch an active schedule.
     * @param databaseInterface
     */

    public FetchActiveScheduleUseCase(ScheduleDataAccess databaseInterface){
        this.databaseInterface = databaseInterface;
    }

    // TODO: consider why we need a FetchActiveScheduleUseCase? lots of reduplicated code => kinda smelly
    /**
     * Returns the active Schedule associated with the given username.
     * @param username - the username of the user.
     * @return active schedule associated with the given username.
     */
    public Schedule getActiveSchedule(String username){
        ScheduleDataAccess.ScheduleInfo schedule = databaseInterface.loadActiveSchedule(username);
        return stringToSchedule(schedule.getId(), schedule.getName(), schedule.getDetails());
    }


    /**
     * Converts string of schedule from database into a Schedule object
     *
     * @param id UUID of schedule
     * @param name string representation of schedule from dat
     * @param scheduleInfo
     * @return a Schedule object
     */
    private Schedule stringToSchedule(String id, String name, List<List<List<Map<String, String>>>> scheduleInfo) {
        Schedule s = new Schedule(name, id);
        for (List<List<Map<String, String>>> day: scheduleInfo) {
            Day d = new Day();
            if (day.size() == 0) {

            }
            else {
                for (Map<String, String> workout : day.get(0)) {
                    Workout w = new Workout(workout.get(databaseInterface.workoutName),
                            Integer.parseInt(workout.get(databaseInterface.calories)));
                    d.addWorkout(w);
                }

                for (Map<String, String> meal : day.get(1)) {
                    Meal m = new Meal(meal.get(databaseInterface.mealName),
                            Integer.parseInt(meal.get(databaseInterface.calories)));
                    d.addMeal(m);
                }
            }

            s.setDay(DayOfWeek.of(scheduleInfo.indexOf(day)+1), d);// index of day + 1 because set day does -1
        }
        return s;
    }
}
