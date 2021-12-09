package Domain.User.UseCase;

import Database.UserDataAccess;
import Domain.User.Boundary.*;

/**
 * Creates a new user account.
 */

public class CreateUserUseCase implements CreateUserInputBoundary {

    private final UserDataAccess databaseInterface;
    private final UserOutputBoundary outputBoundary;

    /**
     * Constructs a use case that can create a user.
     * @param databaseInterface - the access interface to the database
     *
     */
    public CreateUserUseCase(UserDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void createUser(String username, String password, String name, String email) {
        databaseInterface.saveUser(username, password, name, email);
    }
}
