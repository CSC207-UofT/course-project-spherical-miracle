package Schedule;

import java.util.ArrayList;

public interface CreateScheduleInputBoundary {
    /**
     * The User with username creates a schedule with scheduleName.
     * @param scheduleName
     * @param username
     * @param isPublic
     */
    void createSchedule(String scheduleName, String username, boolean isPublic, ArrayList<ArrayList<ArrayList<Object>>> days);
}
