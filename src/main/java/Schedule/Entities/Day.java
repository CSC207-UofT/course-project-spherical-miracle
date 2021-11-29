package Schedule.Entities;//import statements go here

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * A general day in the user's schedule that is not tied to a particular weekday.
 */

//how to attach the specific weekday to a day object?

// will need to also update Schedule based on new structure of storing workouts

// when implementing meals + be a part of the daily summaries
// how should we represent meals? just the name with the calories it has?

public class Day {
    private List<Workout> workouts;
    private List<Meal> meals;
    private int intake;
    private int calBurnt;
    // possible implement dates in the future: ex. November 11,2021

    /**
     * Construct a Day object.
     */
    public Day() {
        // hard coded limit of 5 different types of workouts per day
        workouts = new ArrayList<>();
        meals = new ArrayList<>();
        intake = 0;
        calBurnt = 0;
    }


    /**
     * Returns an array of workouts.
     * @return
     */
    //TODO: Perhaps return an iterator instead of the Array itself to prevent mutations outside of the class.
    public List<Workout> getWorkouts(){
        return workouts;
    }

    /**
     * Add a workout to the list of workouts and return true as long as the list isn't already filled.
     *
     * @return if adding the workout was successful or not
     */
    public List<Meal> getMeals(){
        return meals;
    }

    /**
     * Add a workout to the list of workouts and return true as long as the list isn't already filled.
     *
     * @param workout workout to be added for the day
     * @return if adding the workout was successful or not
     */
    public boolean addWorkout(Workout workout) {
        if (workouts.size() < 5) {
            workouts.add(workout);
            return true;
        }
        else
            return false;
    }

    /**
     * Remove a workout from the list of workouts and return true as long as the workout was removed.
     *
     * @param name name of workout to be removed from day
     * @return if removing the workout was successful or not
     */
    public boolean removeWorkout(String name) {
        for (Workout w: workouts) {
            if (w.getName().equals(name)) {
                calBurnt = calBurnt - w.getCaloriesBurnt();
                    return true;
            }
        }
        return false;
    }

    /**
     * Add a meal to the list of meals and return true as long as the list isn't already filled.
     *
     * @param meal meal to be added for the day
     * @return if adding the meal was successful or not
     */
    public boolean addMeal(Meal meal) {
        for (Meal m: meals){
            if (m.getName().equals(meal.getName())) {
                return false;
            }
        }
        meals.add(meal);
        intake = intake + meal.getCalories();
        return true;
    }

    /**
     * Remove a meal from the list of meals and return true as long as the meal was removed.
     *
     * @param name name of meal to be removed from day
     * @return if removing the meal was successful or not
     */
    public boolean removeMeal(String name) {
        for (Meal m : meals) {
            if (m.getName().equals(name)) {
                intake = intake - m.getCalories();
                return true;
            }
        }
        return false;
    }

    /**
     * Return the net calories burnt for the day.
     *
     * @return net calories burnt for the day.
     */
    public int getTotalCalories() {
        return intake - calBurnt;
    }

    /**
     * Return the absolute calories burnt for the day.
     *
     * @return calories burnt
     */
    public int getOuttake() {
        return calBurnt;
    }

    /**
     * Return the intake of calories for the day.
     *
     * @return calories consumed
     */
    public int getIntake() {
        return intake;
    }

    /**
     * Return a string representation of the workout.
     *
     * @return workout name.
     */
    public String getWorkoutString() {
        StringBuilder stringWorkouts = new StringBuilder();
        for (Workout workout : workouts) {
            if (!(workout == null)) {
                stringWorkouts.append(workout.getName());
                stringWorkouts.append(", ");
            }
        }

        // remove the last comma
        if (stringWorkouts.length() != 0) {
            stringWorkouts = new StringBuilder(stringWorkouts.substring(0, stringWorkouts.length() - 2));
        }

        return stringWorkouts.toString();
    }


    /**
     * Return a string representation of the meal.
     *
     * @return meal name.
     */
    public String getMealString() {
        StringBuilder stringMeals = new StringBuilder();
        for (Meal meal : meals) {
            stringMeals.append(meal.getName());
            stringMeals.append(", ");
        }
        // remove the last comma
        if(stringMeals.length() != 0) {
            stringMeals = new StringBuilder(stringMeals.substring(0, stringMeals.length() - 2));
        }

        return stringMeals.toString();
    }

    /**
     * @param day the integer value of day of week
     * @return returns everything scheduled for this Day
     */
    public String printDay(int day){
        String workout;
        String meal;
        String outputMsg = "";
        if (isEmpty()) {
            workout = "Rest Day";
            meal = "No meals";
        } else if (this.workouts.size() == 0) {
            workout = "Rest Day";
            meal = this.getMealString();
        } else if (this.meals.size() == 0) {
            workout = this.getWorkoutString();
            meal = "No meals";
        } else {
            workout = this.getWorkoutString();
            meal = this.getMealString();
        }
        outputMsg += "This is your plan(s) for " + (DayOfWeek.of(day)) + ": \n Workouts: " + workout + "\n" +
                " Meal: " + meal + "\n ";
        return outputMsg;
        }


    /**
     * Return if this instance of the Day is empty
     *
     * @return if the day is empty
     */
    public boolean isEmpty() {
        return workouts.size() == 0 && meals.size() == 0;
    }
}
