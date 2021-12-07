package Domain.User.UseCase;

import Domain.User.Boundary.*;
import Domain.User.Entities.User;

/**
 * Logs a user into their account when given the correct account information.
 */

public class LoginUseCase implements LoginInputBoundary {


    private final FetchUserUseCase fetchUserUseCase;
    private final UserOutputBoundary outputBoundary;

    /**
     * Constructs a use case that can log a user in.
     *
     * @param outputBoundary output boundary for User
     * @param fetchUserUseCase Use case that enables fetching of users.
     */
    public LoginUseCase(UserOutputBoundary outputBoundary, FetchUserUseCase fetchUserUseCase){
        this.fetchUserUseCase = fetchUserUseCase;
        this.outputBoundary = outputBoundary;
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
            if (user.passwordMatches(password)) {
                outputBoundary.loginMessage(true);
                return LoginResult.SUCCESS;
            }
            outputBoundary.loginMessage(false);
            return LoginResult.INCORRECT_PASSWORD;
        } catch (UserDoesNotExistException e) {
            outputBoundary.loginMessage(false);
            return LoginResult.NO_SUCH_USER;
        }
    }
}
