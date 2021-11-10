import User.*;
import Schedule.ScheduleDataAccess;

import java.util.ArrayList;
import java.util.List;

public class MockDatabase implements UserDataAccess, ScheduleDataAccess {
    private ArrayList<User> users;
    @Override
    public String[] findUserWithUsername(String username) throws UserDoesNotExistException {
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
    public ArrayList<String> loadScheduleWithID(String scheduleID) {
        return new ArrayList<>();
    }

    @Override
    public List<Object> loadUserSchedules(String username) {
        return new ArrayList<>();
    }

    @Override
    public void saveSchedule(String id, String scheduleName, String username, boolean isPublic, ArrayList<ArrayList<ArrayList<Object>>> days) {

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
    public void editUser() {

    }

}
