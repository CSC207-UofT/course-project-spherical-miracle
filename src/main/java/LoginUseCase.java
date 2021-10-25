import java.util.HashMap;

public class LoginUseCase implements LoginInputBoundary {
    private UserDatabase userMap;

    /**
     * Construct a userMap given a HashMap.
     *
     * @param users HashMap of users.
     */
    public LoginUseCase(UserDatabase users){
        this.userMap = users;
    }

    public enum LoginResult {
       SUCCESS, INCORRECT_PASSWORD, NO_SUCH_USER
    }
    /**
     * Login with the given username and password.
     * @param username username of user
     * @param password password of user
     * @return the result of the login
     */
    @Override
    public LoginResult login(String username, String password) {
        if (!userMap.hasUserWithUsername(username))
            return LoginResult.NO_SUCH_USER;
        if (userMap.getUserWithUsername(username).passwordMatches(password))
            return LoginResult.SUCCESS;
        return LoginResult.INCORRECT_PASSWORD;
    }
}
