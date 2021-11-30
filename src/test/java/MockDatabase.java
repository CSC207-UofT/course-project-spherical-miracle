import Schedule.Entities.Day;
import Schedule.Entities.Meal;
import Schedule.Entities.Schedule;
import Schedule.Entities.Workout;
import User.*;
import Schedule.ScheduleDataAccess;
import User.Entities.User;
import User.UseCase.UserDoesNotExistException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDatabase implements UserDataAccess, ScheduleDataAccess {
    private final List<User> users;
    private final Map<String, ScheduleDataAccess.ScheduleInfo> schedules;
    private final Map<String, List<String>> userScheduleMap;
    private final List<String> publicSchedules;
    private String scheduleID ;
    private String scheduleName ;
    private List<List<List<Map<String, String>>>> days;

    @Override
    public void saveUser(String username, String password, String name, String email) {
        User user = new User(username, password, name, email);
        users.add(user);
        userScheduleMap.put(username, new ArrayList<>());
    }

    @Override
    public Object[] loadUserWithUsername(String username) throws UserDoesNotExistException {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return new Object[] {user.getUsername(), user.getPassword(), user.getName(), user.getEmail()};
            }
        }
        throw new UserDoesNotExistException(username);
    }

    public MockDatabase() {
        users = new ArrayList<>();
        schedules = new HashMap<>();
        userScheduleMap = new HashMap<>();
        publicSchedules = new ArrayList<>();
    }

    @Override
    public ScheduleInfo loadScheduleWith(String scheduleID) {
        return schedules.get(scheduleID);
    }

    @Override
    public List<ScheduleInfo> loadSchedulesAssociatedWith(String username) {
        List<ScheduleInfo> scheduleInfos = new ArrayList<>();
        List<String> scheduleIDs = userScheduleMap.get(username);
        for (String scheduleID: scheduleIDs) {
            scheduleInfos.add(loadScheduleWith(scheduleID));
        }
        return scheduleInfos;
    }

    @Override
    public void createSchedule(ScheduleInfo scheduleInfo, String username, boolean isPublic) {
        String id = scheduleInfo.getId();
        schedules.put(id, scheduleInfo);
        userScheduleMap.get(username).add(id);
        if (isPublic)
            publicSchedules.add(id);
    }

    public ScheduleInfo loadActiveSchedule(String username) {
        ScheduleInfo s = new ScheduleInfo(scheduleID, scheduleName, days);
        return s;
    }

    @Override
    public void updateCurrentSchedule(String username, String scheduleId) {};

    @Override
    public List<Object> loadPublicSchedules() {
        List<Object> schedules = new ArrayList<>();
        for (String scheduleID: publicSchedules) {
            schedules.add(this.schedules.get(scheduleID));
        }
        return schedules;
    }


    @Override
    public void editUser(String key, String change, String username) {

    }

    @Override
    public void addHeightWeight(String username, double height, double weight) {
        
    }

    @Override
    public BodyMeasurementRecord getHWListWith(String username) {
        return null;
    }

    public Schedule stringToSchedule(ScheduleInfo scheduleInfo) {
        Schedule s = new Schedule(scheduleInfo.getName(), scheduleInfo.getId());
        for (List<List<Map<String, String>>> day: scheduleInfo.getDetails()) {
            Day d = new Day();

            for (Map<String, String> workout: day.get(0)) {
                Workout w = new Workout(workout.get(ScheduleDataAccess.workoutName),
                        Integer.parseInt(workout.get(ScheduleDataAccess.calories)));
                d.addWorkout(w);
            }

            for (Map<String, String> meal: day.get(1)) {
                Meal m = new Meal(meal.get(ScheduleDataAccess.mealName),
                        Integer.parseInt(meal.get(ScheduleDataAccess.calories)));
                d.addMeal(m);
            }

            s.setDay(DayOfWeek.of(scheduleInfo.getDetails().indexOf(day)+1), d);// index of day + 1 because set day does -1
        }
        return s;
    }

}
