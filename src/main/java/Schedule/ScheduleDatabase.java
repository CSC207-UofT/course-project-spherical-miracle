package Schedule;

import java.util.HashMap;
/**
 * A database for all the schedules for a user.
 */
public class ScheduleDatabase {
    private HashMap<String, Schedule> scheduleMap;

    /**
     * Construct a scheduleMap given a HashMap.
     * @param scheduleMap the given HashMap of schedules
     */
    public ScheduleDatabase(HashMap<String, Schedule> scheduleMap){
        this.scheduleMap = scheduleMap;
    }

    /**
     * Construct a new scheduleMap.
     */
    public ScheduleDatabase(){
        this.scheduleMap = new HashMap<>();
    }

    /**
     * Returns a desired scheduleMap.
     */
    public HashMap<String, Schedule> getScheduleMap() {
        return scheduleMap;
    }

    /**
     * Return the Workout.Schedule based on name.
     * @param name the name of the schedule being returned
     * @return Workout.Schedule
     **/
    public Schedule getSchedule(String name){
        return scheduleMap.get(name);
    }

    /**
     * Add a new Workout.Schedule to the HashMap.
     * @param newSchedule the schedule being added
     **/
    public void addSchedule(Schedule newSchedule){
        scheduleMap.put(newSchedule.getName(), newSchedule);
    }
}
