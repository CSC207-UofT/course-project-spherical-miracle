import java.util.HashMap;

public class ScheduleDatabase {
    private HashMap<String, Schedule> scheduleMap;

    /**
     * Construct a schedulerMap given a HashMap.
     *
     * @param scheduleMap HashMap of schedules.
     */
    public ScheduleDatabase(HashMap<String, Schedule> scheduleMap){
        this.scheduleMap = scheduleMap;
    }

    /**
     * Construct a new scheduleMap.
     *
     *
     */
    public ScheduleDatabase(){
        this.scheduleMap = new HashMap<>();
    }

    public HashMap<String, Schedule> getScheduleMap() {
        return scheduleMap;
    }

    /**
     * Return the Schedule based on name.
     *
     * @return Schedule
     **/
    public Schedule getSchedule(String name){ return scheduleMap.get(name); }

    /**
     * Add a new Schedule to the HashMap.
     *
     *
     **/
    public void addSchedule(Schedule newSchedule){ scheduleMap.put(newSchedule.getName(), newSchedule);}

}
