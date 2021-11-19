package Schedule;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A use case that fetches Schedules from the database.
 */
public class FetchSchedulesUseCase {
    private ScheduleDataAccess databaseInterface;

    /**
     * Constructs a use case that can fetch lists of schedules.
     * @param databaseInterface
     */
    public FetchSchedulesUseCase(ScheduleDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    /**
     * Returns a list of Schedules associated with the given username.
     * @param username - the username of the user.
     * @return list of schedules associated with the given username.
     */
    public List<Schedule> getScheduleAssociatedWith(String username) {
        List<Schedule> userSchedules = new ArrayList<>();
        List<ScheduleDataAccess.ScheduleInfo> schedules = databaseInterface.loadSchedulesAssociatedWith(username);
        for (ScheduleDataAccess.ScheduleInfo scheduleString: schedules) {
            userSchedules.add(stringToSchedule(scheduleString.getId(), scheduleString.getName(), scheduleString.getDetails()));
        }
        return userSchedules;
    }

    /**
     * Returns the list of public schedules.
     * @return list of public schedules.
     */
    public List<Schedule> getPublicSchedules() {
        List<Schedule> publicSchedules = new ArrayList<>();
        List<Object> schedules = databaseInterface.loadPublicSchedules();
        for (Object scheduleString: schedules) {
           // publicSchedules.add(stringToSchedule(scheduleString));
        }
        return publicSchedules;
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

            for (Map<String, String> workout: day.get(0)) {
                Workout w = new Workout(workout.get(databaseInterface.workoutName),
                                        Integer.parseInt(workout.get(databaseInterface.calories)));
                d.addWorkout(w);
            }

            for (Map<String, String> meal: day.get(1)) {
                Meal m = new Meal(meal.get(databaseInterface.mealName),
                                  Integer.parseInt(meal.get(databaseInterface.calories)));
                d.addMeal(m);
            }

            s.setDay(DayOfWeek.of(List.of(scheduleInfo).indexOf(day)+1), d);// index of day + 1 because set day does -1
        }
        return s;
    }
}
