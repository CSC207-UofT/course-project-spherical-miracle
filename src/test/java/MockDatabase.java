import Domain.Schedule.Entities.Day;
import Domain.Schedule.Entities.Meal;
import Domain.Schedule.Entities.Schedule;
import Domain.Schedule.Entities.Workout;
import Database.*;
import Domain.User.Entities.User;
import Domain.User.UseCase.UserDoesNotExistException;
import org.mindrot.jbcrypt.BCrypt;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDatabase implements UserDataAccess, ScheduleDataAccess {
    private final List<User> users;
    public final Map<String, ScheduleInfo> ScheduleIDInfoMap;
    private final Map<String, List<String>> usernameScheduleIDMap;
    private final List<String> publicScheduleIDs;
    private final Map<String, String> usernameActiveIDMap;
    private final Map<String, BodyMeasurementRecord> usernameBodyInfoMap;

    public MockDatabase() {
        users = new ArrayList<>();
        ScheduleIDInfoMap = new HashMap<>();
        usernameScheduleIDMap = new HashMap<>();
        publicScheduleIDs = new ArrayList<>();
        usernameActiveIDMap = new HashMap<>();
        usernameBodyInfoMap = new HashMap<>();
    }

    @Override
    public void saveUser(String username, String password, String name, String email) {
        String pwHash = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User user = new User(username, pwHash, name, email);
        users.add(user);
        usernameScheduleIDMap.put(username, new ArrayList<>());
        usernameBodyInfoMap.put(username, new BodyMeasurementRecord(new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>()));
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
    public ScheduleInfo loadScheduleWithID(String scheduleID) {
        return ScheduleIDInfoMap.get(scheduleID);
    }

    @Override
    public List<ScheduleInfo> loadSchedulesFor(String username) {
        List<ScheduleInfo> scheduleInfos = new ArrayList<>();
        List<String> scheduleIDs = usernameScheduleIDMap.get(username);
        for (String scheduleID: scheduleIDs) {
            scheduleInfos.add(loadScheduleWithID(scheduleID));
        }
        return scheduleInfos;
    }

    @Override
    public void createSchedule(ScheduleInfo scheduleInfo, String username, boolean isPublic) {
        String id = scheduleInfo.getId();
        ScheduleIDInfoMap.put(id, scheduleInfo);
        if (!usernameScheduleIDMap.get(username).contains(id))
            usernameScheduleIDMap.get(username).add(id);
        if (isPublic)
            publicScheduleIDs.add(id);
    }

    @Override
    public ScheduleInfo loadActiveSchedule(String username) {
        return loadScheduleWithID(usernameActiveIDMap.get(username));
    }

    @Override
    public void updateCurrentSchedule(String username, String scheduleId) {
        usernameActiveIDMap.put(username, scheduleId);
    }

    @Override
    public List<ScheduleInfo> loadPublicSchedules() {
        List<ScheduleInfo> schedules = new ArrayList<>();
        for (String scheduleID: publicScheduleIDs) {
            schedules.add(this.ScheduleIDInfoMap.get(scheduleID));
        }
        return schedules;
    }

    private void deleteSchedule(String scheduleID) {
        ScheduleIDInfoMap.remove(scheduleID);
    }

    @Override
    public void deleteUserSchedule(String username, String scheduleId) {
        publicScheduleIDs.remove(scheduleId);
        usernameScheduleIDMap.get(username).remove(scheduleId);
        deleteSchedule(scheduleId);
    }

    @Override
    public void addHeightWeight(String username, double height, double weight) {
        BodyMeasurementRecord b = usernameBodyInfoMap.get(username);
        b.getHeights().add(height);
        b.getWeights().add(weight);
        LocalDate now = LocalDate.now();
        b.getDates().add(now);
    }

    @Override
    public BodyMeasurementRecord getHeightsWeightsFor(String username) {
        return usernameBodyInfoMap.get(username);
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
