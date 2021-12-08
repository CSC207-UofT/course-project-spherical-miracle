package Domain.User.UseCase;

import Domain.User.Boundary.*;
import Domain.User.Entities.User;

/**
 * Logs a user into their account when given the correct account information.
 */

public class LoginUseCase implements LoginInputBoundary {



    /**
     * Constructs a use case that can log a user in.
     */
    public LoginUseCase(){}

    /**
     * The resulting "output" of the LoginResult use case.
     */
    public enum LoginResult {
        SUCCESS, INCORRECT_PASSWORD, NO_SUCH_USER
    }

    @Override
    public LoginResult login(User user, String password) {
        if (user.passwordMatches(password)) {
            return LoginResult.SUCCESS;
        }
        return LoginResult.INCORRECT_PASSWORD;
    }
}
