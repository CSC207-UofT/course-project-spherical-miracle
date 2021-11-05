package Schedule;

import java.util.List;

public interface ScheduleDataAccess {
    String[] loadScheduleWithID(String scheduleID);
    List<Object> loadUserScheduleCollection(String username);
    void saveSchedule(String scheduleName, String username, boolean isPublic);
    List<Object> loadPublicSchedules();
    //TODO: DO we want to have editSchedule()?
}
