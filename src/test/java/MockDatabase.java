import Schedule.Entities.Day;
import Schedule.Entities.Meal;
import Schedule.Entities.Schedule;
import Schedule.Entities.Workout;
import User.*;
import Database.*;
import User.Entities.User;
import User.UseCase.UserDoesNotExistException;
import org.mindrot.jbcrypt.BCrypt;


import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDatabase implements UserDataAccess, ScheduleDataAccess {
    private final List<User> users;
    public final Map<String, ScheduleDataAccess.ScheduleInfo> schedules;
    private final Map<String, List<String>> userScheduleMap;
    private final List<String> publicSchedules;
    private String activeScheduleID ;

    public MockDatabase() {
        users = new ArrayList<>();
        schedules = new HashMap<>();
        userScheduleMap = new HashMap<>();
        publicSchedules = new ArrayList<>();
    }

    @Override
    public void saveUser(String username, String password, String name, String email) {
        String pwHash = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User user = new User(username, pwHash, name, email);
        users.add(user);
        userScheduleMap.put(username, new ArrayList<>());
    }

    @Override
    public UserInfo loadUserWithUsername(String username) throws UserDoesNotExistException {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return new UserInfo(user.getUsername(), user.getPassword(), user.getName(), user.getEmail());
            }
        }
        throw new UserDoesNotExistException(username);
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
        if (!userScheduleMap.get(username).contains(id))
            userScheduleMap.get(username).add(id);
        if (isPublic)
            publicSchedules.add(id);
    }

    public ScheduleInfo loadActiveSchedule(String username) {
        ScheduleInfo s = loadScheduleWith(activeScheduleID);
        return s;
    }

    @Override
    public void updateCurrentSchedule(String username, String scheduleId) {};

    @Override
    public List<ScheduleInfo> loadPublicSchedules() {
        List<ScheduleInfo> schedules = new ArrayList<>();
        for (String scheduleID: publicSchedules) {
            schedules.add(this.schedules.get(scheduleID));
        }
        return schedules;
    }

    @Override
    public void deleteSchedule(String username) {

    }

    @Override
    public void deleteUserSchedule(String username, String scheduleId) {
        schedules.remove(scheduleId);
        if (publicSchedules.contains(scheduleId))
            publicSchedules.remove(scheduleId);
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

    public ScheduleInfo scheduleToString(Schedule schedule) {
        List<List<List<Map<String, String>>>> days = new ArrayList<>();
        for (DayOfWeek c : DayOfWeek.values()) {
            List<List<Map<String, String>>> day = new ArrayList<>();
            Day d = schedule.getDay(c);
            List<Map<String, String>> workouts = new ArrayList<>();
            for (Workout w : d.getWorkouts()) {
                Map<String, String> workout = new HashMap<>();
                workout.put(workoutName, w.getName());
                workout.put(calories, Integer.toString(w.getCalories()));
                workouts.add(workout);
            }
            List<Map<String, String>> meals = new ArrayList<>();
            for (Meal m : d.getMeals()) {
                Map<String, String> meal = new HashMap<>();
                meal.put(mealName, m.getName());
                meal.put(calories, Integer.toString(m.getCalories()));
                meals.add(meal);
            }
            day.add(workouts);
            day.add(meals);
            days.add(day);
        }
        return new ScheduleInfo(schedule.getId(), schedule.getName(), days);
    }
}
