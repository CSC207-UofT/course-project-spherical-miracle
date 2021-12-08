package Adapters;

import Database.UserDataAccess;
import Domain.User.Boundary.*;
import Domain.User.Entities.User;
import Domain.User.UseCase.*;

/**
 * A controller that delegates management of a user's session upon logging in and out.
 */
public class SessionController {

    private final LoginInputBoundary loginInputBoundary;
    private final LogoutInputBoundary logoutInputBoundary;
    private final UserOutputBoundary outputBoundary;
    private final UserDataAccess databaseInterface;
    private boolean loggedIn;

    /**
     * The username of the user that is logged in. Non-empty if and only if LoggedIn is true.
     */
    private String usernameOfLoggedInUser = "";

    /**
     * Constructs a Controller.SessionController with a given database of users to access.
     *
     * @param databaseInterface Interface to access database
     */
    public SessionController(UserDataAccess databaseInterface, UserOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
        this.loginInputBoundary = new LoginUseCase(outputBoundary);
        this.logoutInputBoundary = new LogoutUseCase(outputBoundary);
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
        FetchUserUseCase fetcher = new FetchUserUseCase(databaseInterface);
        LoginUseCase.LoginResult result;
        try {
            result = loginInputBoundary.login(fetcher.getUser(username), password);
        } catch (UserDoesNotExistException e) {
            result = LoginUseCase.LoginResult.NO_SUCH_USER;
        }
        switch (result) {
            case SUCCESS:
                changeLoginStatus();
                usernameOfLoggedInUser = username;
                outputBoundary.loginMessage(true);
                return true;
            case INCORRECT_PASSWORD:
            case NO_SUCH_USER:
                outputBoundary.loginMessage(false);
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Logs user out from the current session.
     */
    public void logout() {
        FetchUserUseCase fetcher = new FetchUserUseCase(databaseInterface);
        try {
            User user = fetcher.getUser(usernameOfLoggedInUser);
            logoutInputBoundary.logout(user.getName());
            changeLoginStatus();
            usernameOfLoggedInUser = "";
        } catch (UserDoesNotExistException e) {
            outputBoundary.print("No such user.");
        }
    }

    /**
     * Returns the username of the user that is logged in. Should only be called if loggedIn.
     */
    public String getUsernameOfLoggedInUser() {
        return usernameOfLoggedInUser;
    }
}
