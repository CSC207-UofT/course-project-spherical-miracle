package User;

import User.Boundary.*;
import User.UseCase.*;

/**
 * A controller that delegates actions for managing a database of users.
 */
public class UserController {

    private final UserDataAccess databaseInterface;
    private final UserOutputBoundary outputBoundary;

    /**

     * Constructs a controller that handles user-related actions.
     *  @param databaseInterface the access interface to the database.
     * @param outputBoundary
     */
    public UserController(UserDataAccess databaseInterface, UserOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Returns true iff successfully creates a user with the given information. All fields must be non-empty.
     * @param username the user's username
     * @param password the user's password
     * @param name the user's name
     * @param email the user's email
     * @return whether the user's info was valid and the user was added to the database or not
     **/
    public boolean createUser(String username, String password, String name, String email) {
        CreateUserInputBoundary createUserInputBoundary = new CreateUserUseCase(databaseInterface, outputBoundary);
        boolean success = createUserInputBoundary.createUser(username, password, name, email);
        return success;
    }

    /**
     * Remove the user from the UserDatabase.
     * @param username the user's username
     */
    public void removeUser(String username) {
        //TODO: validating inputs
        // users.remove(username);
    }
}
