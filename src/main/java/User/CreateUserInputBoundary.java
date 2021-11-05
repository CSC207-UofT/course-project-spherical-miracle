package User;

/**
 * Input boundary for the createUser use case.
 */

public interface CreateUserInputBoundary {

    /**
     * Creates a user.
     * @param name user's name
     * @param username user's desired username
     * @param email user's email
     * @param password user's desired password
     * @return True iff successfully created a user
     */
    boolean createUser(String username, String password, String name, String email);
}
