import Schedule.Entities.Workout;
import Schedule.UseCase.CheckDuplicateWorkoutUseCase;
import Schedule.UseCase.CreateScheduleUseCase;
import Schedule.UseCase.FetchSchedulesUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckDuplicateWorkoutUseCaseTest {

    private final MockDatabase mockDatabase = new MockDatabase();
    private final Presenter presenter = new Presenter();

    @Test
    void checkDuplicateFor() {
        Workout w1 = new Workout("name", 123);
        Workout w1Copy = new Workout("name", 200);
        FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(mockDatabase, presenter);

        CheckDuplicateWorkoutUseCase c = new CheckDuplicateWorkoutUseCase(
                new FetchSchedulesUseCase(mockDatabase, presenter));
//        c.checkDuplicateFor();

    }
}