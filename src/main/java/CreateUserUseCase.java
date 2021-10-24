//TODO: Put in same package as UserDatabase or import UserDatabase

public class CreateUserUseCase implements CreateUserInputBoundary {

    // The database used to store users
    private final UserDatabase users;

    public CreateUserUseCase(UserDatabase users) {
        this.users = users;
    }

    /**
     * Creates a user
     *
     * @param name
     * @param username
     * @param email
     * @param password
     * @return True iff successfully created a user
     */
    @Override
    public boolean createUser(String username, String password, String name, String email) {
        // TODO: Check if username is already in the database
        if (users.hasUserWithUsername(username)) {
            return false; // Maybe throw a specific exception instead of returning false? e.g. UserAlreadyExistsException
        }
        User user = new User(username, password, name, email);
        users.save(user);
        // Should the validating process be done in saveUser? Or another method
        // TODO: Use the data access interface to save the user into the UserDatabase
        return true;
    }
}
