import Schedule.Day;
import Schedule.Meal;
import Schedule.Workout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {

    private Day day;
    private Workout[] workouts;
    private Meal[] meals;
    private int totalCalBurnt;
    private int totalIntake;

    @BeforeEach
    void setUp() {
        day = new Day();
        workouts = new Workout[]{new Workout("Hip Thrusts", 103), new Workout("Eating", 0),
                new Workout("Running", 342), new Workout("Deadlift", 59),
                new Workout("Bench Press", 68)};
        meals = new Meal[]{new Meal("Coffee", 103), new Meal("Salt", 0),
                new Meal("Steak", 342)};
        totalCalBurnt = 0;
        for (Workout workout: workouts) {
            totalCalBurnt = totalCalBurnt + workout.getCaloriesBurnt();
        }

        totalIntake = 0;
        for (Meal meal: meals) {
            totalIntake = totalIntake + meal.getCalories();
        }
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Adds workouts and meals to the day
     */
    public void addToDay() {
        for (Workout workout: workouts) {
            day.addWorkout(workout);
        }
        for (Meal meal: meals) {
            day.addMeal(meal);
        }
    }

    @Test
    void getWorkouts() {
        addToDay();
        List<Workout> dayWorkouts = day.getWorkouts();
        for (int i = 0; i < workouts.length; i++){
            assert workouts[i].getName().equals(dayWorkouts.get(i).getName());
            assert workouts[i].getCaloriesBurnt() == dayWorkouts.get(i).getCaloriesBurnt();
        }
    }

    @Test
    void getMeals() {
        addToDay();
        List<Meal> dayMeals = day.getMeals();
        for (int i = 0; i < meals.length; i++){
            assert meals[i].getName().equals(dayMeals.get(i).getName());
            assert meals[i].getCalories() == dayMeals.get(i).getCalories();
        }
    }

    @Test
    void addWorkout() {
        int calBurntAdded = 0;
        for (Workout workout: workouts) {
            assert day.addWorkout(workout);
            calBurntAdded = calBurntAdded + workout.getCaloriesBurnt();
            assert day.getOuttake() == calBurntAdded;
        }
        assert !day.addWorkout(workouts[0]);
    }

    @Test
    void removeWorkout() {
        int calBurntRemoved = 0;
        addToDay();
        for (Workout workout: workouts) {
            assert day.removeWorkout(workout);
            calBurntRemoved = calBurntRemoved + workout.getCaloriesBurnt();
            assert day.getOuttake() == totalCalBurnt - calBurntRemoved;
        }
        assert !day.removeWorkout(workouts[0]);
    }

    @Test
    void addMeal() {
        int intakeAdded = 0;
        for (Meal meal: meals) {
            assert day.addMeal(meal);
            intakeAdded = intakeAdded + meal.getCalories();
            assert day.getIntake() == intakeAdded;
        }
    }

    @Test
    void removeMeal() {
        int intakeRemoved = 0;
        addToDay();
        for (Meal meal: meals) {
            assert day.removeMeal(meal);
            intakeRemoved = intakeRemoved + meal.getCalories();
            assert day.getIntake() == totalIntake - intakeRemoved;
        }
        assert !day.removeMeal(meals[0]);
    }

    @Test
    void getTotalCalories() {
        addToDay();
        assert day.getTotalCalories() == totalIntake - totalCalBurnt;
    }

    @Test
    void getOuttake() {
        addToDay();
        assert day.getOuttake() == totalCalBurnt;
    }

    @Test
    void getIntake() {
        addToDay();
        assert day.getIntake() == totalIntake;
    }

    @Test
    void getWorkoutString() {
        assert day.getWorkoutString().equals("");
        day.addWorkout(new Workout("Bicep Curl", 100));
        assert day.getWorkoutString().equals("Bicep Curl");
        day.removeWorkout(new Workout("Bicep Curl", 100));
        assert day.getWorkoutString().equals("");
        addToDay();
        assert day.getWorkoutString().equals("Hip Thrusts, Eating, Running, Deadlift, Bench Press");
    }

    @Test
    void getMealString() {
        assert day.getMealString().equals("");
        day.addMeal(new Meal("Raw Chicken", 50));
        assert day.getMealString().equals("Raw Chicken");
        day.removeMeal(new Meal("Raw Chicken", 50));
        assert day.getMealString().equals("");
        addToDay();
        assert day.getMealString().equals("Coffee, Salt, Steak");
    }

    @Test
    void printDay() {
        System.out.println(day.printDay(1));
        assert day.printDay(1).equals("This is your plan(s) for " + (DayOfWeek.of(1)) + ": \n Workouts: "
                + "Rest Day" + "\n" + " Meal: " + "No meals" + "\n ");
        addToDay();
        assert day.printDay(3).equals("This is your plan(s) for " + (DayOfWeek.of(3)) + ": \n Workouts: "
                + "Hip Thrusts, Eating, Running, Deadlift, Bench Press" + "\n" + " Meal: "
                + "Coffee, Salt, Steak" + "\n ");
    }

    @Test
    void isEmpty() {
        assert day.isEmpty();
        addToDay();
        assert !day.isEmpty();
    }
}