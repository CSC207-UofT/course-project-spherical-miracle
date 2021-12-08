package Domain.Schedule.UseCase;

import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Domain.Schedule.Entities.*;

public class DisplayScheduleUseCase {

    private final ScheduleOutputBoundary outputBoundary;

    public DisplayScheduleUseCase(ScheduleOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void displaySchedule(Schedule schedule) {
         outputBoundary.scheduleInfoMessage(schedule.toString());
    }

}
