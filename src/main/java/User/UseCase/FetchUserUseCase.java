package User.UseCase;

import User.Entities.User;
import Database.UserDataAccess;

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
        Object[] userInfo = databaseInterface.loadUserWithUsername(username);
        if(userInfo[4] == null) {
            return new User((String) userInfo[0], (String) userInfo[1], (String) userInfo[2], (String) userInfo[3]);
        }else{
        return new User((String) userInfo[0], (String) userInfo[1], (String) userInfo[2], (String) userInfo[3], (double) userInfo[4], (double) userInfo[5]);
        }
    }


}
