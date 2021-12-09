package Domain.User.UseCase;

import Domain.User.Boundary.*;
import Domain.User.Entities.User;
import Domain.User.Boundary.LoginResult;

/**
 * Logs a user into their account when given the correct account information.
 */

public class LoginUseCase implements LoginInputBoundary {



    /**
     * Constructs a use case that can log a user in.
     */
    public LoginUseCase(){}

    @Override
    public LoginResult login(User user, String password) {
        if (user.passwordMatches(password)) {
            return LoginResult.SUCCESS;
        }
        return LoginResult.INCORRECT_PASSWORD;
    }
}
