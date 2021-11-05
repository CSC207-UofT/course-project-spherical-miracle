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
    // private String usernameOfCurrentUser;

    /**
     * Constructs a SessionController with a given database of users to access.
     * @param database Interface to access database
     */
    public SessionController(UserDataAccess database) {
        FetchUserUseCase data = new FetchUserUseCase(database);
        this.loginInputBoundary = new LoginUseCase(data);
        this.logoutInputBoundary = new LogoutUseCase();
    }
    // public SessionController(User.LoginInputBoundary loginIB, logoutIB){}

    /**
     * Logs user in with the given username and password.
     * @param username the given username
     * @param password the given password
     * @return whether the user was able to successfully log in or not
     */
    public boolean login(String username, String password) {
        LoginUseCase.LoginResult result = loginInputBoundary.login(username, password);
        // maybe throw exceptions when it fails?
        switch (result) {
            case SUCCESS:
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
     * @return whether the user was successfully logged out or not
     */
    public boolean logout() {
        return logoutInputBoundary.logout();
    }
}
