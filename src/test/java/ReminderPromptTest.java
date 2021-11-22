import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReminderPromptTest {

    private MockDatabase mockDatabase;
    @BeforeEach
    void setUp() {
        mockDatabase = new MockDatabase();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void remind() {
        List<List<List<Map<String, String>>>> days = new ArrayList<>();
        Map<String, String> workout = new HashMap<>();
//        workout.put("workoutName", "testName");
//        workout.put("calories", "123");
        Map<String, String> meal = new HashMap<>();
//        meal.put("mealName", "testName");
//        meal.put("calories", "123");
        List<Map<String, String>> workouts = new ArrayList<>();
//        workouts.add(workout);
        List<Map<String, String>> meals = new ArrayList<>();
//        meals.add(meal);
        for (DayOfWeek c: DayOfWeek.values()) {
            days.add(new ArrayList<>());
        }
        LocalDate.now();

        days.get(4).add(workouts);
        days.get(4).add(meals);

        UUID u = UUID.randomUUID();
        mockDatabase.saveSchedule(u.toString(), "TestName", "michael", true, days);
        ReminderPromptUseCase reminder = new ReminderPromptUseCase();
        System.out.println(reminder.remind("michael", mockDatabase));
    }

}