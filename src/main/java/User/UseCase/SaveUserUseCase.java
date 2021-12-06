package User.UseCase;

import User.Entities.User;
import User.UserDataAccess;

public class SaveUserUseCase {

    private final UserDataAccess databaseInterface;

    /**
     * Constructs a use case that can save a user's personal information.
     * @param databaseInterface interface for database
     */
    public SaveUserUseCase(UserDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    /**
     * Saves the given User object to the database.
     * @param user - the user object associated with a user.
     */
    public void saveUser(User user) {
        databaseInterface.saveUser(user.getUsername(), user.getPassword(), user.getName(), user.getEmail());
    }
}
