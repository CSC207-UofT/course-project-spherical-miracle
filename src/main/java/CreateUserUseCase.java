//TODO: Put in same package as UserDatabase or import UserDatabase

import javax.xml.crypto.Data;

public class CreateUserUseCase implements CreateUserInputBoundary {

    private final DataAccessInterface database;

    public CreateUserUseCase(DataAccessInterface database) {
        this.database = database;
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
        FetchUserUseCase fetch = new FetchUserUseCase(database);
        try {
            fetch.getUser(username);
            return false;
        } catch (UserDoesNotExistException e) {
            SaveUserUseCase save = new SaveUserUseCase(database);
            save.saveUser(new User(username, password, name, email));
            return true;
        }
        // Should the validating process be done in saveUser? Or another method
    }
}
