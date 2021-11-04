/**
 * Logs a user out of their account.
 */
public class LogoutUseCase implements LogoutInputBoundary {

    /**
     * Constructs a new LogoutUseCase.
     */
    public LogoutUseCase() {
    }

    /**
     * Returns true.
     */
    @Override
    public boolean logout() {
        return true;
    }
}
