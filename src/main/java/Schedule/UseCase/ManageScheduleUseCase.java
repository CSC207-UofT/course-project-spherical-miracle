package Schedule.UseCase;

import Schedule.Boundary.CreateScheduleInputBoundary;
import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.Entities.*;
import Schedule.ScheduleDataAccess;
import Schedule.Entities.Day.addWorkoutResult;

import java.time.DayOfWeek;
import java.util.*;

public class ManageScheduleUseCase implements CreateScheduleInputBoundary {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    /**
     * Instantiate a use case that creates a schedule.
     * @param databaseInterface - the access interface boundary between the database and the use case.
     */
    public ManageScheduleUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Creates a schedule and prompts the user for the necessary details.
     * @param name - specified name of the schedule
     * @param username - username of the user creating a schedule
     */
    public String createSchedule(String name, String username) {
        Schedule schedule = new Schedule(name);
        editSchedule(schedule);
        saveSchedule(schedule, username);
        return schedule.getId();
    }

    /**
     *
     * @param scheduleID
     * @param username
     */
    public void editSchedule(String scheduleID, String username) {
        FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
        RemoveScheduleUseCase remove = new RemoveScheduleUseCase(databaseInterface, outputBoundary);
        Schedule schedule = fetch.getScheduleWithID(scheduleID);
        editSchedule(schedule);
        remove.remove(scheduleID, username);
        saveSchedule(schedule, username);
    }

    /**
     *
     * @param schedule
     * @param username
     */
    protected void saveSchedule(Schedule schedule, String username) {
        boolean isPublic = outputBoundary.isPublic();
        databaseInterface.createSchedule(scheduleToString(schedule), username, isPublic);
    }

    /**
     *
     * @param schedule
     */
    private void editSchedule(Schedule schedule) {
        String option = outputBoundary.selectEditOrSave();
        while (option.equals("c")) {
            DayOfWeek dayOfWeek = outputBoundary.selectDay();
            schedule.setDay(dayOfWeek, getDay(schedule.getDay(dayOfWeek)));
            option = outputBoundary.selectEditOrSave();
        }
        assert option.equals("s");
    }

    /**
     *
     * @param day
     * @return
     */
    private Day getDay(Day day) {
        String option;
        ScheduleEntityFactory factory = new ScheduleEntityFactory();
        while (true) {
            option = outputBoundary.createDayPrompt();
            if (option.equals("f"))
                return day;
            Map<String, String> nameAndCalories;
            String type;
            if (option.equals("w"))
                type = "Workout";
            else
                type = "Meal";
            nameAndCalories = outputBoundary.getNameAndCalories(type);
            if (option.equals("w")) {
                Workout w = (Workout) factory.getScheduleEntity(option, nameAndCalories.get("name"),
                        Integer.parseInt(nameAndCalories.get("calories")));
                addWorkoutResult result = day.addWorkout(w);
                outputBoundary.showAddWorkoutResult(result.ordinal(), nameAndCalories.get("name"));
            }
            else
                day.addMeal((Meal) factory.getScheduleEntity(option, nameAndCalories.get("name"),
                        Integer.parseInt(nameAndCalories.get("calories"))));
        }
    }

    /**
     *
     * @param name - the desired name of the schedule
     * @param username - the username of the User creating the schedule
     * @param isPublic - whether this schedule is public
     * @param days - the details of this schedule
     */
    @Override
    public void createSchedule(String name, String username, boolean isPublic, List<List<List<Map<String, String>>>> days) {
        Schedule schedule = new Schedule(name);
        databaseInterface.createSchedule(scheduleToString(schedule), username, isPublic);
    }

    /**
     * 
     * @param schedule
     * @return
     */
    private ScheduleDataAccess.ScheduleInfo scheduleToString(Schedule schedule) {
        List<List<List<Map<String, String>>>> days = new ArrayList<>();
        for (DayOfWeek c: DayOfWeek.values()) {
            List<List<Map<String, String>>> day = new ArrayList<>();
            Day d = schedule.getDay(c);
            List<Map<String, String>> workouts = new ArrayList<>();
            for (Workout w: d.getWorkouts()) {
                Map<String, String> workout = new HashMap<>();
                workout.put(databaseInterface.workoutName, w.getName());
                workout.put(databaseInterface.calories, Integer.toString(w.getCalories()));
                workouts.add(workout);
            }
            List<Map<String, String>> meals = new ArrayList<>();
            for (Meal m: d.getMeals()) {
                Map<String, String> meal = new HashMap<>();
                meal.put(databaseInterface.mealName, m.getName());
                meal.put(databaseInterface.calories, Integer.toString(m.getCalories()));
                meals.add(meal);
            }
            day.add(workouts);
            day.add(meals);
            days.add(day);
        }
        return new ScheduleDataAccess.ScheduleInfo(schedule.getId(), schedule.getName(), days);
    }
}
