
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
    private FetchSchedulesUseCase fetch;
    private String uuid;
    private Schedule schedule1;

    @BeforeEach
    void setUp() {
        mockDatabase = new MockDatabase();
        fetch = new FetchSchedulesUseCase(mockDatabase, presenter);
        mockDatabase.saveUser("username", "password", "name", "email@email.com");
        uuid = UUID.randomUUID().toString();
        schedule1 = new Schedule("U1", uuid);
    }

    void setSchedule() {
        SetActiveScheduleUseCase active = new SetActiveScheduleUseCase(mockDatabase, presenter);
        mockDatabase.createSchedule(new ScheduleDataAccess.ScheduleInfo(uuid, "U1", new ArrayList<>()),
                "username", false);
        active.setAsActiveSchedule("username", schedule1);
    }

    @Test
    void getActiveSchedule() {
        assert fetch.getActiveSchedule("username") == null;
        setSchedule();
        assert fetch.getActiveSchedule("username").getId().equals(schedule1.getId());
    }

    @Test
    void getScheduleAssociatedWith() {
        assert fetch.getScheduleAssociatedWith("username").isEmpty();
        setSchedule();
        assert fetch.getScheduleAssociatedWith("username").get(0).equals(uuid);
    }
}