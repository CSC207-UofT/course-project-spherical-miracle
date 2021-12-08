package Domain.Schedule.UseCase;

import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Domain.Schedule.Entities.*;

public class DisplayScheduleUseCase {

    private final ScheduleOutputBoundary outputBoundary;
    /**
     * Instantiate a use case that displays the detail of a schedule
     * @param outputBoundary - output boundary for use case and presenter
     */
    public DisplayScheduleUseCase(ScheduleOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Display the detailed information of the given schedule
     * @param schedule - the schedule to be displayed
     */
    public void displaySchedule(Schedule schedule) {
         outputBoundary.scheduleInfoMessage(schedule.toString());
    }

}
