package Schedule;

public interface ScheduleDataAccess {
    String[] loadSchedule();
    String[] loadUserScheduleCollection(String username);
    void saveSchedule(String scheduleName, String username, boolean isPublic);
    //TODO: DO we want to have editSchedule()?
}
