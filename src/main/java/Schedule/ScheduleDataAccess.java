package Schedule;

public interface ScheduleDataAccess {
    String[] loadSchedule();
    String[] loadUserScheduleCollection(String username);
    void saveSchedule(String schedule_name, Boolean is_public);
    //TODO: DO we want to have editSchedule()?
}
