package Domain.User.UseCase;

import Domain.User.Entities.User;
import Database.UserDataAccess;
import Database.UserDataAccess.UserInfo;

/**
 * A use case which gets information about the user from database
 */
public class FetchUserUseCase {

    private final UserDataAccess databaseInterface;

    /**
     * Constructs a use case that can fetch a specified user.
     * @param databaseInterface interface of database
     */
    public FetchUserUseCase(UserDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    /**
     * Returns a User object associated with an existing user with the given username.
     * @param username - the username of the desired user
     * @return a User object with information of the desired user
     * @throws UserDoesNotExistException - if no existing users have the given username
     */
    public User getUser(String username) throws UserDoesNotExistException {
        UserInfo userInfo = databaseInterface.loadUserWithUsername(username);
        if (userInfo.hasBodyMeasurements()) {
            return new User(username, userInfo.getPassword(), userInfo.getName(), userInfo.getEmail(),
                    userInfo.getHeight(), userInfo.getWeight());
        } else {
            return new User(username, userInfo.getPassword(), userInfo.getName(), userInfo.getEmail());
        }
    }
}
