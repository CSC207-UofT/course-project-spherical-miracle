package Schedule.Boundary;
import java.util.List;

public interface ScheduleOutputBoundary {
    void scheduleMadeMessage(String returnMessage);
    void scheduleInfoMessage(String name);
    void something(boolean signedUp);
    void listSchedules(List<String> schedules);
    void deleteSchedule(String scheduleName);

    void outputTooManyWorkout();
}
