package Adapters;

import Database.UserDataAccess;
import User.Boundary.*;
import User.UseCase.*;

/**
 * A controller that delegates management of a user's session upon logging in and out.
 */
public class SessionController {

    private final LoginInputBoundary loginInputBoundary;
    private final LogoutInputBoundary logoutInputBoundary;
    private boolean loggedIn;

    /**
     * The username of the user that is logged in. Non-empty if and only if LoggedIn is true.
     */
    private String usernameOfLoggedInUser = "";
    private String workingScheduleID = "";

    /**
     * Constructs a Controller.SessionController with a given database of users to access.
     *
     * @param databaseInterface Interface to access database
     */
    public SessionController(UserDataAccess databaseInterface, UserOutputBoundary outputBoundary) {
        FetchUserUseCase fetch = new FetchUserUseCase(databaseInterface);
        this.loginInputBoundary = new LoginUseCase(outputBoundary, fetch);
        this.logoutInputBoundary = new LogoutUseCase(outputBoundary, fetch);
    }

    public void setWorkingScheduleID(String scheduleID) {
        this.workingScheduleID = scheduleID;
    }

    public String getWorkingScheduleID() {
        return workingScheduleID;
    }

    /**
     * Changes the logged in status.
     */
    private void changeLoginStatus() {
        loggedIn = !loggedIn;
    }

    /**
     * Logs user in with the given username and password.
     *
     * @param username the given username
     * @param password the given password
     * @return whether the user was able to successfully log in or not
     */
    public boolean login(String username, String password) {
        LoginUseCase.LoginResult result = loginInputBoundary.login(username, password);
        // TODO: maybe throw exceptions when it fails?
        switch (result) {
            case SUCCESS:
                changeLoginStatus();
                usernameOfLoggedInUser = username;
                return true;
            case INCORRECT_PASSWORD:
            case NO_SUCH_USER:
                return false;
            default:
                throw new IllegalArgumentException();
        }
        // usernameOfCurrentUser = username;
    }

    /**
     * Logs user out from the current session.
     */
    public void logout() {
        logoutInputBoundary.logout(usernameOfLoggedInUser);
        changeLoginStatus();
        usernameOfLoggedInUser = "";
    }

    /**
     * Returns whether a user is logged in.
     * @return whether user is logged in
     */
    public boolean loggedIn() {
        return loggedIn;
    }

    /**
     * Returns the username of the user that is logged in. Should only be called if loggedIn.
     */
    public String getUsernameOfLoggedInUser() {
        return usernameOfLoggedInUser;
    }
}
