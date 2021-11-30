package User.UseCase;

import User.Entities.User;
import User.UserDataAccess;

public class FetchUserUseCase {

    private final UserDataAccess databaseInterface;

    /**
     * Constructs a use case that can fetch a specified user.
     * @param databaseInterface
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
        Object[] userInfo = databaseInterface.loadUserWithUsername(username);
        User user = new User((String)userInfo[0], (String)userInfo[1], (String)userInfo[2] , (String)userInfo[3]);
        return user;
    }


}
