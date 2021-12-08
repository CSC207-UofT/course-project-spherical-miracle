import Domain.Schedule.Entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.time.DayOfWeek;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.util.List;


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
            totalCalBurnt = totalCalBurnt + workout.getCalories();
        }

        totalIntake = 0;
        for (Meal meal: meals) {
            totalIntake = totalIntake + meal.getCalories();
        }
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
            assert workouts[i].getCalories() == dayWorkouts.get(i).getCalories();
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
        for (Workout workout: workouts) {
            assert day.addWorkout(workout) == Day.addWorkoutResult.SUCCESS;
        }
        day.removeWorkout(workouts[1].getName());
        assert day.addWorkout(workouts[0]) == Day.addWorkoutResult.DUPLICATE_NAME;
        day.addWorkout(workouts[1]);
        assert day.addWorkout(new Workout("Balls", 103)) == Day.addWorkoutResult.TOO_MANY;
    }

    @Test
    void removeWorkout() {
        int calBurntRemoved = 0;
        addToDay();
        for (Workout workout: workouts) {
            assert day.removeWorkout(workout.getName());
            calBurntRemoved = calBurntRemoved + workout.getCalories();
            assert day.getOuttake() == totalCalBurnt - calBurntRemoved;
        }
        assert !day.removeWorkout(workouts[0].getName());
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
    @Order(4)
    void removeMeal() {
        int intakeRemoved = 0;
        addToDay();
        for (Meal meal: meals) {
            assert day.removeMeal(meal.getName());
            intakeRemoved = intakeRemoved + meal.getCalories();
            assert day.getIntake() == totalIntake - intakeRemoved;
        }
        assert !day.removeMeal(meals[0].getName());
    }

    @Test
    void getTotalCalories() {
        addToDay();
        assert day.getTotalCalories() == totalIntake - totalCalBurnt;
    }

    @Test
    void getOuttake() {
        addToDay();
        assert day.getTotalCalories() == totalIntake - totalCalBurnt;
    }

    @Test
    void getIntake() {
        addToDay();
        assert day.getTotalCalories() == totalIntake - totalCalBurnt;
    }

    @Test
    void getWorkoutString() {
        assert day.getWorkoutString().equals("");
        day.addWorkout(new Workout("Bicep Curl", 100));
        assert day.getWorkoutString().equals("Bicep Curl: 100 kcal");
        day.removeWorkout("Bicep Curl");
        assert day.getWorkoutString().equals("");
        addToDay();
        assert day.getWorkoutString().equals("Hip Thrusts: 103 kcal, Eating: 0 kcal, " +
                "Running: 342 kcal, Deadlift: 59 kcal, Bench Press: 68 kcal");
    }

    @Test
    void getMealString() {
        assert day.getMealString().equals("");
        day.addMeal(new Meal("Protein Shake", 300));
        assert day.getMealString().equals("Protein Shake: 300 kcal");
        day.removeMeal("Protein Shake");
        assert day.getMealString().equals("");
        addToDay();
        System.out.println(day.getMealString());
        assert day.getMealString().equals("Coffee: 103 kcal, Salt: 0 kcal, Steak: 342 kcal");
    }

    @Test
    void printDay() {
        System.out.println(day.printDay(1));
        assert day.printDay(1).equals("This is your plan(s) for " + (DayOfWeek.of(1)) + ": \n Workouts: "
                + "Rest Day" + "\n" + " Meal: " + "No meals" + "\n ");
        addToDay();
        assert day.printDay(3).equals("This is your plan(s) for " + (DayOfWeek.of(3)) + ": \n Workouts: "
                + "Hip Thrusts: 103 kcal, Eating: 0 kcal, Running: 342 kcal, Deadlift: 59 kcal, Bench Press: 68 kcal" +
                "\n" + " Meal: " + "Coffee: 103 kcal, Salt: 0 kcal, Steak: 342 kcal" + "\n ");
    }

    @Test
    void isEmpty() {
        assert day.isEmpty();
        addToDay();
        assert !day.isEmpty();
    }
}