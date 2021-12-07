import Schedule.Boundary.ScheduleOutputBoundary;
import Database.ScheduleDataAccess;
import Schedule.UseCase.FetchSchedulesUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FetchSchedulesUseCaseTest {

    private ScheduleDataAccess mockDatabase;
//    private ScheduleOutputBoundary presenter = new ScheduleOutputBoundary();

    @BeforeEach
    void setUp() {
        mockDatabase = new MockDatabase();
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
//        FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(mockDatabase, presenter);

    }

    @Test
    void getScheduleAssociatedWith() {
    }

    @Test
    void getPublicSchedules() {
    }
}