import Schedule.ScheduleController;
import User.UserController;

import java.time.DayOfWeek;
import java.util.*;

/**
 * The main controller for delegating requests and actions.
 */

public class MainController {

    private final UserController userController;
    private final ScheduleController scheduleController;
    private final SessionController sessionController;

    /**
     * Constructs a MainController object.
     */
    public MainController(DataAccess databaseGateway, Presenter presenter) {
        userController = new UserController(databaseGateway, presenter);
        scheduleController = new ScheduleController(databaseGateway, presenter);
        sessionController = new SessionController(databaseGateway, presenter);
    }

    /**
     * Creates a new account with given details from the user.
     */
    public boolean signup(String username, String password, String name, String email) {
        if (userController.createUser(username, password, name, email)) {
            return login(username, password);
        }
        return false;
    }

    /**
     * Attempts to log into an account with the given username and password.
     */
    public boolean login(String username, String password) {
        return sessionController.login(username, password);
    }

    /**
     * Logs out from an account.
     */
    public void logout() {
        sessionController.logout();
    }

    /**
     * Creates a new empty schedule with the desired name.
     */
    public void createSchedule(String scheduleName) {
        String username = sessionController.getUsernameOfLoggedInUser();
        scheduleController.createSchedule(scheduleName, username);
    }

    /**
     * Allows user to view all their schedules and potentially perform an action on them.
     */
    public void viewMySchedules() {
        String username = sessionController.getUsernameOfLoggedInUser();
        List<String> ids = scheduleController.viewListOfSchedule(username);
        scheduleController.DisplayDeleteActivateSchedule(username, ids);
    }

    /**
     * Allows user to view all public schedules.
     */
    public void viewPublicSchedules() {
        scheduleController.viewPublicSchedules();
    }

    /**
     * Sends a user a reminder of the plans for a specific day.
     */
    public void sendReminderForDay(DayOfWeek dayOfWeek) {
        String username = sessionController.getUsernameOfLoggedInUser();
        scheduleController.reminderFor(username, dayOfWeek);
    }

    /**
     * Adds or updates a user's height and weight measurements.
     */
    public void addHeightWeight(){
        String username = sessionController.getUsernameOfLoggedInUser();
        userController.addHeightWeight(username);
    }

    /**
     * Gets the current height, weight, and BMI of the user.
     */
    public void currentWeightHeightBMI(){
        String username = sessionController.getUsernameOfLoggedInUser();
        userController.getCurrentWeightHeightBMI(username);
    }
}
