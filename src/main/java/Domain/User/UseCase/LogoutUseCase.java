package Domain.User.UseCase;

import Domain.User.Boundary.*;

/**
 * Logs a user out of their account.
 */
public class LogoutUseCase implements LogoutInputBoundary {

    private final UserOutputBoundary outputBoundary;

    /**
     * Constructs a use case that logs a user out.
     * @param outputBoundary output boundary for User
     *
     */
    public LogoutUseCase(UserOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Returns true iff the user is successfully logged out.
     * @param name - the name of the user
     */
    @Override
    public boolean logout(String name) {
        outputBoundary.logoutMessage(name);
        return true;
    }
}
