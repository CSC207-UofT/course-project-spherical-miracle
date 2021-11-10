package Schedule;

import java.util.ArrayList;
import java.util.List;

public class FetchSchedulesUseCase {
    private ScheduleDataAccess scheduleDatabase;

    public FetchSchedulesUseCase(ScheduleDataAccess scheduleDatabase) {
        this.scheduleDatabase = scheduleDatabase;
    }

    public List<Schedule> getUserSchedules(String username) {
        ArrayList<Schedule> userSchedules = new ArrayList<>();
        List<Object> schedules = scheduleDatabase.loadUserSchedules(username);
        for (Object scheduleString: schedules) {
            userSchedules.add(toSchedule(scheduleString));
        }
        return userSchedules;
    }
    
    public List<Schedule> getPublicSchedules() {
        ArrayList<Schedule> publicSchedules = new ArrayList<>();
        // TODO: fix toSchedule
        List<Object> schedules = scheduleDatabase.loadPublicSchedules();
        for (Object scheduleString: schedules) {
           publicSchedules.add(toSchedule(scheduleString));
        }
        return publicSchedules;
    }

    private Schedule toSchedule(Object scheduleInfo){
        if (scheduleInfo.getClass() == ArrayList.class) {
            ArrayList<Object> info = (ArrayList) scheduleInfo;
            return new Schedule((String) info.get(0), (String) info.get(1));
        }
        return null;
    }
}
