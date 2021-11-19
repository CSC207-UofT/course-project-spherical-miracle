package User;
/**
 * Creates a new user account.
 */

public class CreateUserUseCase implements CreateUserInputBoundary {

    private final UserDataAccess databaseInterface;

    /**
     * Constructs a use case that can create a user.
     * @param databaseInterface - the access interface to the database
     */
    public CreateUserUseCase(UserDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public boolean createUser(String username, String password, String name, String email) {
        FetchUserUseCase fetch = new FetchUserUseCase(databaseInterface);
        try {
            fetch.getUser(username);
            return false;
        } catch (UserDoesNotExistException e) {
            SaveUserUseCase save = new SaveUserUseCase(databaseInterface);
            save.saveUser(new User(username, password, name, email));
            return true;
        }
    }
}
