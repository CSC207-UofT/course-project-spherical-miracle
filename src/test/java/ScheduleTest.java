import Domain.Schedule.Entities.Day;
import Domain.Schedule.Entities.Schedule;
import Domain.Schedule.Entities.Workout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class ScheduleTest {
    String val;
    Schedule week1;

    @BeforeEach
    void setUp() {
        val = UUID.randomUUID().toString();
        week1 = new Schedule("First week!", val);
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void getName(){
        assert week1.getName().equals("First week!");
    }

    @Test
    void setName(){
        week1.setName("actually this is the second week");
        assert week1.getName().equals("actually this is the second week");
    }

    @Test
    void getId() {
        assert week1.getId().equals(val);
    }

    @Test
    void setDay() {
        Day day = new Day();
        day.addWorkout(new Workout("Bench Press", 130));
        week1.setDay(DayOfWeek.MONDAY, day);
        assert week1.getDay(DayOfWeek.MONDAY) == day;
    }

    @Test
    void cancelDay() {
        Day day = new Day();
        day.addWorkout(new Workout("Bench Press", 130));
        week1.setDay(DayOfWeek.MONDAY, day);
        week1.cancelDay(0);
        assert !week1.getDay(DayOfWeek.MONDAY).equals(day);
    }

    @Test
    void getDay() {
        Day day = week1.getDay(DayOfWeek.MONDAY);
        assert day.getIntake() == 0;
        assert day.getOuttake() == 0;
        assert day.getMeals().isEmpty();
        assert day.getWorkouts().isEmpty();
        assert day.isEmpty();
        assert day.getTotalCalories() == 0;
        Workout workout = new Workout("Bench Press", 130);
        day.addWorkout(workout);
        week1.setDay(DayOfWeek.MONDAY, day);
        assert day.getIntake() == 0;
        assert day.getOuttake() == 130;
        assert day.getMeals().isEmpty();
        List<Workout> workoutList = new ArrayList<>();
        workoutList.add(workout);
        assert day.getWorkouts().equals(workoutList);
        assert !day.isEmpty();
        assert day.getTotalCalories() == -130;
    }

    @Test
    void testToString() {
        Day day = new Day();
        day.addWorkout(new Workout("Bench Press", 130));
        week1.setDay(DayOfWeek.MONDAY, day);
        String plan = """
                This is your plan(s) for MONDAY:
                 Workouts: Bench Press: 130 kcal
                 Meal: No meals
                This is your plan(s) for TUESDAY:
                 Workouts: Rest Day
                 Meal: No meals
                This is your plan(s) for WEDNESDAY:
                 Workouts: Rest Day
                 Meal: No meals
                This is your plan(s) for THURSDAY:
                 Workouts: Rest Day
                 Meal: No meals
                This is your plan(s) for FRIDAY:
                 Workouts: Rest Day
                 Meal: No meals
                This is your plan(s) for SATURDAY:
                 Workouts: Rest Day
                 Meal: No meals
                This is your plan(s) for SUNDAY:
                 Workouts: Rest Day
                 Meal: No meals
                """;
        System.out.println(week1.toString());
        assert week1.toString().equals(plan);
    }
}
