import Adapters.Presenter;
import Domain.Schedule.Entities.Schedule;
import Domain.Schedule.UseCase.DisplayScheduleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DisplayScheduleUseCaseTest {
    DisplayScheduleUseCase display;
    Schedule schedule;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        Presenter p = new Presenter();
        display = new DisplayScheduleUseCase(p);
        schedule = new Schedule("Balls");
    }

    @Test
    void displaySchedule() {
        display.displaySchedule(schedule);
        String plan = """
                This is your plan(s) for MONDAY:
                 Workouts: Rest Day
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
//        assert outContent.toString().equals(plan);
    }
}