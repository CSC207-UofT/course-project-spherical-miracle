import java.util.HashMap;

public class LoginUseCase implements LoginInputBoundary {

    private final FetchUserUseCase database;

    /**
     * Construct a userMap given a HashMap.
     *
     * @param database Use case that enables fetching of users.
     */
    public LoginUseCase(FetchUserUseCase database){
        this.database = database;
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
//        try {
//            User user = database.getUser(username);
//        } catch (UserDoesNotExistException e) {
//            return LoginResult.NO_SUCH_USER;
//        }
        User user = database.getUser(username);
        if (user == null)
            return LoginResult.NO_SUCH_USER;
        if (user.passwordMatches(password))
            return LoginResult.SUCCESS;
        return LoginResult.INCORRECT_PASSWORD;
    }
}
