package Schedule.UseCase;

import Schedule.Boundary.CreateScheduleInputBoundary;
import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.Entities.Day;
import Schedule.Entities.Meal;
import Schedule.Entities.Schedule;
import Schedule.Entities.Workout;
import Schedule.ScheduleDataAccess;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateScheduleUseCase implements CreateScheduleInputBoundary {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    /**
     * Instantiate a use case that creates a schedule.
     * @param databaseInterface - the access interface boundary between the databaseInterface and the use case.
     */
    public CreateScheduleUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     *
     * @param name
     * @param username
     */
    public String createSchedule(String name, String username) {
        Schedule schedule = new Schedule(name);
        databaseInterface.createSchedule(scheduleToString(schedule), username, false);
        return schedule.getId();
    }

    public boolean appendWorkout(String workoutName, String calories, String scheduleID, DayOfWeek dayOfWeek) {
        Workout workout = new Workout(workoutName, Integer.parseInt(calories));
        FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
        Schedule schedule = fetch.getScheduleWithID(scheduleID);
        Day day = schedule.getDay(dayOfWeek);
        boolean success = day.addWorkout(workout);
        if (!success)
            outputBoundary.outputTooManyWorkout();
        return success;
    }

    @Override
    public void createSchedule(String name, String username, boolean isPublic, List<List<List<Map<String, String>>>> days) {
        Schedule schedule = new Schedule(name);
        databaseInterface.createSchedule(scheduleToString(schedule), username, isPublic);
    }

    // TODO: Have subpackages within the Schedule package and make this protected (only other use cases have access)
    public void createSchedule(Schedule schedule, String username, boolean isPublic) {
        databaseInterface.createSchedule(scheduleToString(schedule), username, isPublic);
    }

    private ScheduleDataAccess.ScheduleInfo scheduleToString(Schedule schedule) {
        List<List<List<Map<String, String>>>> days = new ArrayList<>();
        for (DayOfWeek c: DayOfWeek.values()) {
            List<List<Map<String, String>>> day = new ArrayList<>();
            Day d = schedule.getDay(c);
            List<Map<String, String>> workouts = new ArrayList<>();
            for (Workout w: d.getWorkouts()) {
                Map<String, String> workout = new HashMap<>();
                workout.put(databaseInterface.workoutName, w.getName());
                workout.put(databaseInterface.calories, Integer.toString(w.getCaloriesBurnt()));
                workouts.add(workout);
            }
            List<Map<String, String>> meals = new ArrayList<>();
            for (Meal m: d.getMeals()) {
                Map<String, String> meal = new HashMap<>();
                meal.put(databaseInterface.workoutName, m.getName());
                meal.put(databaseInterface.calories, Integer.toString(m.getCalories()));
                meals.add(meal);
            }
            day.add(workouts);
            day.add(meals);
            days.add(day);
        }
        return new ScheduleDataAccess.ScheduleInfo(schedule.getId(), schedule.getName(), days);
//        databaseInterface.createSchedule(scheduleInfo, days);
//        outputBoundary.scheduleMadeMessage(schedule.printSchedule());
    }
}