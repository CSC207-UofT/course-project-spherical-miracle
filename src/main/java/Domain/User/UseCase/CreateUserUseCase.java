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
     * @param outputBoundary - the output boundary
     */
    public CreateUserUseCase(UserDataAccess databaseInterface, UserOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public boolean createUser(String username, String password, String name, String email) {
        databaseInterface.saveUser(username, password, name, email);
        outputBoundary.signupMessage(true);
        return true;
    }
}
