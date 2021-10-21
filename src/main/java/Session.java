/**
 * The current login state.
 */
// TODO: LoginState vs Session?
public class Session {

    private boolean loggedIn = false;

    // null iff logged out?
    private User currentUser;

    public Session() {}

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login(User user) {
        currentUser = user;
        loggedIn = true;
    }

    public void logout(User user) {
        currentUser = null; // how to avoid this design?
        loggedIn = false;
    }

    public User getLoggedInUser() {
        return currentUser;
    }
}
