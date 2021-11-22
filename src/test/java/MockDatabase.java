import User.*;
import Schedule.ScheduleDataAccess;

import java.util.ArrayList;
import java.util.List;

public class MockDatabase implements UserDataAccess, ScheduleDataAccess {
    private ArrayList<User> users;
    @Override
    public String[] loadUserWithUsername(String username) throws UserDoesNotExistException {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return new String[] {user.getUsername(), user.getPassword(), user.getName(), user.getEmail()};
            }
        }
        throw new UserDoesNotExistException(username);
    }

    public MockDatabase() {
        users = new ArrayList<User>();
    }

    @Override
    public ScheduleInfo loadScheduleWithID(String scheduleID) {
        return null;
    }

    @Override
    public List<ScheduleInfo> loadSchedulesAssociatedWith(String username) {
        return new ArrayList<>();
    }

    @Override
    public void createSchedule(ScheduleInfo scheduleInfo, String username, boolean isPublic) {

    }

    @Override
    public void updateCurrentSchedule(String username, String scheduleId) {};

    @Override
    public List<Object> loadPublicSchedules() {
        return new ArrayList<>();
    }

    @Override
    public void saveUser(String username, String password, String name, String email) {
        User user = new User(username, password, name, email);
        users.add(user);
    }

    @Override
    public void editUser(String key, String change, String username) {

    }

}
