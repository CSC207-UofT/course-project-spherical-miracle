
import Adapters.Presenter;
import Database.ScheduleDataAccess;
import Domain.Schedule.Entities.Schedule;
import Domain.Schedule.UseCase.FetchSchedulesUseCase;
import Domain.Schedule.UseCase.SetActiveScheduleUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

class FetchSchedulesUseCaseTest {

    private MockDatabase mockDatabase;
    private Presenter presenter = new Presenter();
    private FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(mockDatabase, presenter);

    @BeforeEach
    void setUp() {
        mockDatabase = new MockDatabase();
        mockDatabase.saveUser("username", "password", "name", "email@email.com");
        mockDatabase.saveUser("username2", "password", "name", "email@email.com");
        mockDatabase.createSchedule(new ScheduleDataAccess.ScheduleInfo("1", "U1", new ArrayList<>()),
                "username", false);
        mockDatabase.createSchedule(new ScheduleDataAccess.ScheduleInfo("2", "P1", new ArrayList<>()),
                "username", true);
        mockDatabase.createSchedule(new ScheduleDataAccess.ScheduleInfo("2", "P2", new ArrayList<>()),
                "username2", true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getActiveSchedule() {
        assert fetch.getActiveSchedule("username") == null;
        SetActiveScheduleUseCase active = new SetActiveScheduleUseCase(mockDatabase, presenter);
        String uuid = UUID.randomUUID().toString();
        Schedule schedule1 = new Schedule("U1", uuid);
        active.setAsActiveSchedule("username", schedule1);
        assert fetch.getActiveSchedule("username") == schedule1;
    }

    @Test
    void getScheduleAssociatedWith() {
    }

    @Test
    void getPublicSchedules() {
    }

    @Test
    void testGetActiveSchedule() {
    }

    @Test
    void testGetScheduleAssociatedWith() {
    }

    @Test
    void testGetPublicSchedules() {
    }

    @Test
    void getScheduleWithID() {
    }
}