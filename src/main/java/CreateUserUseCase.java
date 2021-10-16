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
    public boolean createUser(String name, String username, String email, String password) {
        // TODO: Check if username is already in the database
        // if (userIn(database)) {
        //      return false;
        // }
        User user = new User(name, username, email, password);
        users.save(user);
        // Should the validating process be done in saveUser? Or another method
        // TODO: Use the data access interface to save the user into the UserDatabase
        return true;
    }
}
