import User.*;

/**
 * A controller that delegates management of a user's session upon logging in and out.
 */
public class SessionController {

    /**
     * An input boundary for the login use case.
     */
    private final LoginInputBoundary loginInputBoundary;

    /**
     * An input boundary for the logout use case.
     */
    private final LogoutInputBoundary logoutInputBoundary;

    /**
     * Whether there is a user logged in for this session. Is false by default.
     */
    private boolean loggedIn;

    /**
     * The username of the user that is logged in. Non-empty if and only if isLoggedIn is true.
     */
    private String usernameOfLoggedInUser = "";


    /**
     * Constructs a SessionController with a given database of users to access.
     *
     * @param database Interface to access database
     */
    public SessionController(UserDataAccess database) {
        FetchUserUseCase data = new FetchUserUseCase(database);
        this.loginInputBoundary = new LoginUseCase(data);
        this.logoutInputBoundary = new LogoutUseCase();
    }
    // public SessionController(User.LoginInputBoundary loginIB, logoutIB){}

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
        logoutInputBoundary.logout();
        changeLoginStatus();
        usernameOfLoggedInUser = "";
    }

    /**
     * Returns whether a user is logged in.
     * @return
     */
    public boolean loggedIn() {
        return loggedIn;
    }

    /**
     * Returns the username of the user that is logged in. Should only be called if loggedIn.
     */
    public String getUsernameOfLoggedInUser() throws NotLoggedInException {
        if (!loggedIn) {
            throw new NotLoggedInException();
        }
        return usernameOfLoggedInUser;
    }
}
