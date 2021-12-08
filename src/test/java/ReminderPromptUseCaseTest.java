import Adapters.Presenter;
import Domain.Schedule.Entities.Schedule;
import Domain.Schedule.Entities.Day;
import Domain.Schedule.Entities.Workout;
import Domain.Schedule.UseCase.ReminderPromptUseCase;

import Domain.Schedule.UseCase.ManageScheduleUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

class ReminderPromptTest {

    private MockDatabase mockDatabase;
    @BeforeEach
    void setUp() {
        mockDatabase = new MockDatabase();
    }

    @Test
    void remind() {
        Presenter p = new Presenter();
        ReminderPromptUseCase reminder = new ReminderPromptUseCase(mockDatabase, p);
        ManageScheduleUseCase c = new ManageScheduleUseCase(mockDatabase, p);
        assert reminder.remind("michael", DayOfWeek.MONDAY) ==  null;
//        c.createSchedule(s, "michael", true);

        Schedule s = new Schedule("TestName");
        Day expectedDay = new Day();
        expectedDay.addWorkout(new Workout("WorkoutName", 120));
        s.setDay(LocalDate.now().getDayOfWeek(), expectedDay);
        Day otherDay = new Day();
        otherDay.addWorkout(new Workout("NotThisWorkout", 130));
        s.setDay(LocalDate.now().getDayOfWeek().plus(1), otherDay);

        assert reminder.remind("michael", LocalDate.now().getDayOfWeek()).
                equals(expectedDay.printDay(LocalDate.now().getDayOfWeek().getValue()));
    }

}