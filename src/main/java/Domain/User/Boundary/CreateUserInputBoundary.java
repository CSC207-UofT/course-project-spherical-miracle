package Domain.User.Boundary;

/**
 * Input boundary for the createUser use case.
 */
public interface CreateUserInputBoundary {

    /**
     * Creates a user.
     * @param username desired username of the user
     * @param password desired password of the user
     * @param name name of the user
     * @param email email of the user
     */
    void createUser(String username, String password, String name, String email);
}
