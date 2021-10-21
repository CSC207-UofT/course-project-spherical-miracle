public class SessionController {

    private final LoginInputBoundary loginInputBoundary;
    private final LogoutInputBoundary logoutInputBoundary;
    // private String usernameOfCurrentUser;

    public SessionController(UserDatabase users) {
        this.loginInputBoundary = new LoginUseCase(users);
        this.logoutInputBoundary = new LogoutUseCase();
    }

    public boolean login(String username, String password) {
        boolean result = loginInputBoundary.login(username, password);
        if (!result) {
            // throw IncorrectCredentialsException? Or simply return false and let UI handle?
        }
        // usernameOfCurrentUser = username;
        return result;
    }

    public boolean logout() {
        return logoutInputBoundary.logout();
    }
}
