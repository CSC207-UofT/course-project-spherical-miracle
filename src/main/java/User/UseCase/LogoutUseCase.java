package User.UseCase;

import User.Boundary.LogoutInputBoundary;
import User.Entities.User;
import User.UserDoesNotExistException;

/**
 * Logs a user out of their account.
 */
public class LogoutUseCase implements LogoutInputBoundary {

    private final FetchUserUseCase fetchUserUseCase;
    private final CreateUserUseCase.UserOutputBoundary outputBoundary;

    /**
     * Constructs a use case that logs a user out.
     * @param outputBoundary
     * @param fetchUserUseCase
     */
    public LogoutUseCase(CreateUserUseCase.UserOutputBoundary outputBoundary, FetchUserUseCase fetchUserUseCase) {
        this.outputBoundary = outputBoundary;
        this.fetchUserUseCase = fetchUserUseCase;
    }

    /**
     * Returns true iff the user is successfully logged out.
     */
    @Override
    public boolean logout(String username) {
        try {
            User user = fetchUserUseCase.getUser(username);
            outputBoundary.logoutMessage(user.getName());
            return true;
        } catch (UserDoesNotExistException e) {
            return false;
        }
    }
}
