package User;

/**
 * Logs a user out of their account.
 */
public class LogoutUseCase implements LogoutInputBoundary {

    /**
     * Constructs a use case that logs a user out.
     */
    public LogoutUseCase() {
    }

    /**
     * Returns true iff the user is successfully logged out.
     */
    @Override
    public boolean logout() {
        return true;
    }
}
