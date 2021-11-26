import Schedule.ScheduleController;
import User.UserController;

import java.util.*;
import java.util.regex.Pattern;

public class MainController {

    private final UserController userController;
    private final ScheduleController scheduleController;
    private final SessionController sessionController;
    private final Presenter presenter;

    public MainController(DataAccess databaseGateway, Presenter presenter) {
        userController = new UserController(databaseGateway, presenter);
        scheduleController = new ScheduleController(databaseGateway, presenter);
        sessionController = new SessionController(databaseGateway, presenter);
        this.presenter = presenter;
    }

    private void createSchedule() {

    }

    public void signup(String username, String password, String name, String email) {
        userController.createUser(username, password, name, email);
        login(username, password);
    }

    public void login(String username, String password) {
        sessionController.login(username, password);
    }

    public void logout() {
        sessionController.logout();
    }

    public void createSchedule(String scheduleName) {
        List<List<List<Map<String, String>>>> days = new ArrayList<>();
        String username = sessionController.getUsernameOfLoggedInUser();
        scheduleController.createSchedule(scheduleName, username, false, days);
    }

    public void viewMySchedules() {
        String username = sessionController.getUsernameOfLoggedInUser();
        scheduleController.viewListOfSchedule(username);
    }

    public void viewPublicSchedules() {
        System.out.println("NOT IMPLEMENTED YET");
    }

}
