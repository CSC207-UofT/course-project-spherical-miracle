import javax.xml.crypto.Data;

public class SessionController {

    private final LoginInputBoundary loginInputBoundary;
    private final LogoutInputBoundary logoutInputBoundary;
    // private String usernameOfCurrentUser;

    public SessionController(DataAccessInterface database) {
        FetchUserUseCase data = new FetchUserUseCase(database);
        this.loginInputBoundary = new LoginUseCase(data);
        this.logoutInputBoundary = new LogoutUseCase();
    }
    // public SessionController(LoginInputBoundary loginIB, logoutIB){}

    /**
     * Login with the given username and password.
     * @param username
     * @param password
     * @return
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
     * Logout from this session.
     * @return
     */
    public boolean logout() {
        return logoutInputBoundary.logout();
    }
}
