package Schedule.Boundary;

import java.util.List;
import java.util.Map;

public interface CreateScheduleInputBoundary {
    /**
     * Creates a schedule with given information.
     * @param name - the desired name of the schedule
     * @param username - the username of the User creating the schedule
     * @param isPublic - whether this schedule is public
     * @param days - the details of this schedule
     */
    void createSchedule(String name, String username, boolean isPublic, List<List<List<Map<String, String>>>> days);
}
