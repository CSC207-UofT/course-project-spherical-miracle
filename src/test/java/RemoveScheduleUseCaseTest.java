import Adapters.Presenter;
import Domain.Schedule.Entities.Schedule;
import Domain.Schedule.UseCase.RemoveScheduleUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

//    @Test
//    void remove() {
//        Schedule s = new Schedule("s");
//        mock.createSchedule(mock.scheduleToString(s), username, false);
//        remover.removeSchedule(username, "s");
//        assert mock.ScheduleIDInfoMap.isEmpty();
//    }
//
//    @Test
//    void remove1() {
//        Schedule s1 = new Schedule("s");
//        Schedule s2 = new Schedule("s");
//        mock.createSchedule(mock.scheduleToString(s1), username, false);
//        mock.createSchedule(mock.scheduleToString(s2), username, false);
//        remover.removeSchedule(username, "s");
//        assert mock.ScheduleIDInfoMap.size() == 1;
//        remover.removeSchedule(username, "s");
//    }

    @Test
    void editRemoveSchedule() {

    }
}