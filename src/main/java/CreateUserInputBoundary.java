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
    boolean createUser(String username, String password, String name, String email);
}
