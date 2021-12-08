import Adapters.Presenter;
import Domain.Schedule.Entities.Schedule;
import Domain.Schedule.Entities.Day;
import Domain.Schedule.Entities.Workout;
import Domain.Schedule.UseCase.ReminderPromptUseCase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ReminderPromptUseCaseTest {

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
        Schedule s = new Schedule("TestName");
        Day expectedDay = new Day();
        expectedDay.addWorkout(new Workout("WorkoutName", 120));
        s.setDay(LocalDate.now().getDayOfWeek(), expectedDay);
        Day otherDay = new Day();
        otherDay.addWorkout(new Workout("NotThisWorkout", 130));
        s.setDay(LocalDate.now().getDayOfWeek().plus(1), otherDay);
        Presenter p = new Presenter();
        ReminderPromptUseCase reminder = new ReminderPromptUseCase(p);
        assert reminder.remind(LocalDate.now().getDayOfWeek(), s).
                equals(expectedDay.printDay(LocalDate.now().getDayOfWeek().getValue()));
    }

}