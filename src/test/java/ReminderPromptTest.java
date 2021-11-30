import Schedule.Entities.Schedule;
import Schedule.Entities.Day;
import Schedule.Entities.Workout;
import Schedule.ReminderPromptUseCase;

import Schedule.UseCase.ManageScheduleUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        Schedule s = new Schedule("TestName");
        Day expectedDay = new Day();
        expectedDay.addWorkout(new Workout("WorkoutName", 120));
        s.setDay(LocalDate.now().getDayOfWeek(), expectedDay);
        Day otherDay = new Day();
        otherDay.addWorkout(new Workout("NotThisWorkout", 130));
        s.setDay(LocalDate.now().getDayOfWeek().plus(1), otherDay);

        Presenter p = new Presenter();
        ManageScheduleUseCase c = new ManageScheduleUseCase(mockDatabase, p);
//        c.createSchedule(s, "michael", true);
        ReminderPromptUseCase reminder = new ReminderPromptUseCase(mockDatabase, p);
        assert reminder.remind("michael", LocalDate.now().getDayOfWeek()).
                equals(expectedDay.printDay(LocalDate.now().getDayOfWeek().getValue()));
    }

}