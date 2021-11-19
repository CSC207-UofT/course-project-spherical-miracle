package Schedule;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleFormatConverter {

    private final ScheduleDataAccess database;

    public ScheduleFormatConverter(ScheduleDataAccess database) {
        this.database = database;
    }

    public ScheduleDataAccess.ScheduleInfo scheduleToString(Schedule schedule) {
        List<List<List<Map<String, String>>>> days = new ArrayList<>();
        for (DayOfWeek c: DayOfWeek.values()) {
            List<List<Map<String, String>>> day = new ArrayList<>();
            Day d = schedule.getDay(c);
            List<Map<String, String>> workouts = new ArrayList<>();
            for (Workout w: d.getWorkouts()) {
                Map<String, String> workout = new HashMap<>();
                workout.put(database.workoutName, w.getName());
                workout.put(database.calories, Integer.toString(w.getCaloriesBurnt()));
                workouts.add(workout);
            }
            List<Map<String, String>> meals = new ArrayList<>();
            for (Meal m: d.getMeals()) {
                Map<String, String> meal = new HashMap<>();
                meal.put(database.workoutName, m.getName());
                meal.put(database.calories, Integer.toString(m.getCalories()));
                meals.add(meal);
            }
            day.add(workouts);
            day.add(meals);
            days.add(day);
        }
        return new ScheduleDataAccess.ScheduleInfo(schedule.getId(), schedule.getName(), days);
    }


}
