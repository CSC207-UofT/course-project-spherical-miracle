/**
 * Input boundary for the createUser use case.
 */

public interface CreateUserInputBoundary {
    /**
     * Creates a user
     * @param name
     * @param username
     * @param email
     * @param password
     * @return True iff successfully created a user
     */
    boolean createUser(String name, String username, String email, String password);
}
