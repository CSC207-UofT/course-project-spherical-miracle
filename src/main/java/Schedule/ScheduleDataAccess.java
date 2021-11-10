package Schedule;

import java.util.ArrayList;
import java.util.List;

public interface ScheduleDataAccess {
    String[] loadScheduleWithID(String scheduleID);
    List<Object> loadUserScheduleCollection(String username);
    void saveSchedule(String id, String scheduleName, String username, boolean isPublic, ArrayList<ArrayList<ArrayList<Object>>> days);
    List<Object> loadPublicSchedules();
    //TODO: DO we want to have editSchedule()?
}
