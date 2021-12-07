package Schedule.UseCase;

import java.time.DayOfWeek;
import java.util.*;

import Schedule.Boundary.CreateScheduleInputBoundary;
import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.Entities.*;
import Schedule.ScheduleDataAccess;
import Schedule.ScheduleDataAccess.ScheduleInfo;
import Schedule.Entities.Day.addWorkoutResult;

/**
 * A use case that handles the creation and changes to any schedule.
 */
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

    @Override
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
        remove.editRemoveSchedule(username, scheduleID);
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

    private void editSchedule(Schedule schedule) {
        String option = outputBoundary.selectEditOptions();
        while (!option.equalsIgnoreCase("s")) {
            if (option.equals("c")) {
                DayOfWeek dayOfWeek = outputBoundary.selectDay();
                schedule.setDay(dayOfWeek, getEditedDay(schedule.getDay(dayOfWeek)));
            } else if (option.equals("n")) {
                String oldName = schedule.getName();
                String newName = outputBoundary.getName();
                schedule.setName(newName);
                outputBoundary.showNameChange(oldName, newName);
            }
            option = outputBoundary.selectEditOptions();
        }
    }

    private Day getEditedDay(Day day) {
        String option;
        ScheduleEntityFactory factory = new ScheduleEntityFactory();
        while (true) {
            option = outputBoundary.createDayPrompt();
            if (option.equals("f"))
                return day;
            String type, name;
            type = (option.charAt(option.length() - 1) == 'w') ? "Workout" : "Meal";
            Map<String, String> nameAndCalories;
            List<String> names = new ArrayList<>();
            switch (option) {
                case "cw":
                    nameAndCalories = outputBoundary.getNameAndCalories(type);
                    Workout w = (Workout) factory.getScheduleEntity("w", nameAndCalories.get("name"),
                            Integer.parseInt(nameAndCalories.get("calories")));
                    addWorkoutResult result = day.addWorkout(w);
                    outputBoundary.showAddWorkoutResult(result.ordinal(), nameAndCalories.get("name"));
                    break;
                case "cm":
                    nameAndCalories = outputBoundary.getNameAndCalories(type);
                    day.addMeal((Meal) factory.getScheduleEntity("m", nameAndCalories.get("name"),
                            Integer.parseInt(nameAndCalories.get("calories"))));
                    break;
                case "rw":
                    // TODO: is there a way to make this better code?
                    for (Workout workout : day.getWorkouts()) {
                        names.add(workout.getName());
                    }
                    if (!names.isEmpty()) {
                        name = outputBoundary.selectByName(names);
                        System.out.println(day.removeWorkout(name));
                        System.out.println("Workout '" + name + "' has been removed!");
                    }
                    else {
                        System.out.println("There are no workouts to be found.");
                    }
                    break;
                case "rm":
                    for (Meal meal: day.getMeals()) {
                        names.add(meal.getName());
                    }
                    if (names.isEmpty()) {
                        name = outputBoundary.selectByName(names);
                        day.removeMeal(name);
                        System.out.println("Meal '" + name + "' has been removed!");
                    }
                    else {
                        System.out.println("There are no meals to be found.");
                    }
                    break;
            }
        }
    }

    private ScheduleInfo scheduleToString(Schedule schedule) {
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
        return new ScheduleInfo(schedule.getId(), schedule.getName(), days);
    }
}
