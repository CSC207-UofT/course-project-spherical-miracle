package Schedule.Boundary;

import java.util.List;
import java.util.Map;

public interface CreateScheduleInputBoundary {
    /**
     * Creates a schedule with given information.
     * @param name - the desired name of the schedule
     * @param username - the username of the User creating the schedule
     */
    String createSchedule(String name, String username);
}
