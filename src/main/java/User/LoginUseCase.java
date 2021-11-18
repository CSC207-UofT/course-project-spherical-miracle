package User;

/**
 * Logs a user into their account when given the correct account information.
 */

public class LoginUseCase implements LoginInputBoundary {


    private final FetchUserUseCase fetchUserUseCase;

    /**
     * Constructs a use case that can log a user in.
     *
     * @param fetchUserUseCase Use case that enables fetching of users.
     */
    public LoginUseCase(FetchUserUseCase fetchUserUseCase){
        this.fetchUserUseCase = fetchUserUseCase;
    }

    /**
     * The resulting "output" of the LoginResult use case.
     */
    public enum LoginResult {
        SUCCESS, INCORRECT_PASSWORD, NO_SUCH_USER
    }

    @Override
    public LoginResult login(String username, String password) {
        try {
            User user = fetchUserUseCase.getUser(username);
            if (user.passwordMatches(password))
                return LoginResult.SUCCESS;
            return LoginResult.INCORRECT_PASSWORD;
        } catch (UserDoesNotExistException e) {
            return LoginResult.NO_SUCH_USER;
        }
    }
}
