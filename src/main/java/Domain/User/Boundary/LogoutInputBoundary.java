package Domain.User.Boundary;

/**
 * Input boundary for the logout use case.
 */
public interface LogoutInputBoundary {
    boolean logout(String username);
}
