package Schedule.UseCase;

import Schedule.Entities.*;

import java.time.DayOfWeek;

public class CheckDuplicateWorkoutUseCase {

    private FetchSchedulesUseCase fetch;

    public CheckDuplicateWorkoutUseCase(FetchSchedulesUseCase fetch) {
        this.fetch = fetch;
    }

    public boolean checkDuplicateFor(String workoutName, DayOfWeek dayOfWeek, String scheduleID) {
        Schedule schedule = fetch.getScheduleWithID(scheduleID);
        Day day = schedule.getDay(dayOfWeek);
        for (Workout workout : day.getWorkouts()) {
            if (workoutName.equals(workout.getName()))
                return true;
        }
        return false;
    }

}
