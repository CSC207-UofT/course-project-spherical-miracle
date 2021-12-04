import Schedule.Entities.Schedule;
import Schedule.UseCase.RemoveScheduleUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveScheduleUseCaseTest {

    private MockDatabase mock = new MockDatabase();
    private RemoveScheduleUseCase remover;
    private String username = "username1";
    private Presenter presenter = new Presenter();

    @BeforeEach
    void setUp() {
        remover = new RemoveScheduleUseCase(mock, presenter);
        mock.saveUser(username, "", "", "");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void remove() {
        Schedule s = new Schedule("s");
        mock.createSchedule(mock.scheduleToString(s), username, false);
        remover.remove(username, "s");
        assert mock.schedules.isEmpty();
    }

    @Test
    void remove1() {
        Schedule s1 = new Schedule("s");
        Schedule s2 = new Schedule("s");
        mock.createSchedule(mock.scheduleToString(s1), username, false);
        mock.createSchedule(mock.scheduleToString(s2), username, false);
        remover.remove(username, "s");
        assert mock.schedules.size() == 1;
    }
}