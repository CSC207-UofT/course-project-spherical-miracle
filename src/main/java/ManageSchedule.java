import java.util.Arrays;
// Controller

public class ManageSchedule {

    private String name;
    private ScheduleDatabase manager;


    /**
     * Construct a list of the information needed to create a new user and the UserDatabase data.
     *
     * @param name string of the schedule name.
     * @param manager ScheduleDatabse object
     */
    public ManageSchedule(String name, ScheduleDatabase manager){
        this.manager = manager;
        this.name = name;
    }

    /**
     * If the information is valid, add the user to the UserDatabase object then return true. Otherwise, return false.
     *
     * @return boolean
     **/
    public void AddSchedule() {
        //TODO: validating inputs
        //boolean is_valid = ;
        //if (is_valid) {
            Schedule new_Schedule = new Schedule(name);
            manager.getScheduleMap().put(name, new_Schedule);
            //return true;
        //}
        //return false;
    }
    public void RemoveSchedule(String name) {
        //TODO: validating inputs
        manager.getScheduleMap().remove(name);
    }
}
