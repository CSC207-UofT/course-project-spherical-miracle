package Schedule.UseCase;

import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.Entities.*;

public class DisplayScheduleUseCase {

    private final ScheduleOutputBoundary outputBoundary;

    public DisplayScheduleUseCase(ScheduleOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void displaySchedule(Schedule schedule) {
         outputBoundary.scheduleInfoMessage(schedule.toString());
    }

}
