import User.*;
import Schedule.ScheduleDataAccess;

import java.util.ArrayList;

public class MockDatabase implements UserDataAccess, ScheduleDataAccess {
    private ArrayList<User> users;
    @Override
    public String[] findUser(String username) throws UserDoesNotExistException {
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
    public String[] loadSchedule() {
        return new String[0];
    }

    @Override
    public String[] loadUserScheduleCollection(String username) {
        return new String[0];
    }

    @Override
    public void saveSchedule(String schedule_name, Boolean is_public) {

    }

    @Override
    public void saveUserScheduleCollection() {

    }

    @Override
    public void editUserScheduleCollection() {

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