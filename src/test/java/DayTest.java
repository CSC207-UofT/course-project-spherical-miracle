import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Schedule.Entities.*;

class DayTest {

    private Day day;
    private Workout[] workouts;
    private Meal[] meals;
    @BeforeEach
    void setUp() {
        day = new Day();
        workouts = new Workout[]{new Workout("Hip thrusts", 103), new Workout("Eating", 0),
                new Workout("Running", 342), new Workout("Deadlift", 59),
                new Workout("Bench Press", 68)};
        meals = new Meal[]{new Meal("Coffee", 103), new Meal("Salt", 0),
                new Meal("Steak", 342)};
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getWorkouts() {
    }

    @Test
    void getMeals() {

    }

    @Test
    @Order(1)
    void addWorkout() {
        for (Workout workout: workouts) {
            assert day.addWorkout(workout) == Day.addWorkoutResult.SUCCESS;
        }
        assert day.addWorkout(workouts[0]) == Day.addWorkoutResult.DUPLICATE_NAME;
    }

    @Test
    @Order(2)
    void removeWorkout() {
//        for (Workout workout: workouts) {
//            assert day.removeWorkout(workout);
//        }
//        assert !day.removeWorkout(workouts[0]);
    }

    @Test
    @Order(3)
    void addMeal() {
        for (Meal meal: meals) {
            assert day.addMeal(meal);
        }
    }

    @Test
    @Order(4)
    void removeMeal() {
//        for (Meal meal: meals) {
//            assert day.removeMeal(meal);
//        }
//        assert !day.removeMeal(meals[0]);
    }

    @Test
    void getTotalCalories() {
    }

    @Test
    void getOuttake() {
    }

    @Test
    void getIntake() {
    }

    @Test
    void getWorkoutString() {
    }

    @Test
    void getMealString() {
    }

    @Test
    void printDay() {
    }

    @Test
    void isEmpty() {
    }
}