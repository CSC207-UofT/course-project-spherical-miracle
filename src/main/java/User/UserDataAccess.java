package User;

import User.UseCase.UserDoesNotExistException;

public interface UserDataAccess {

    /**
     * Returns an array of personal information of the specified user.
     * @param username - username of the target user
     * @return an array containing the personal information of the user.
     * In the order of username, password, name, email.
     * @throws UserDoesNotExistException when no existing users have the specified username.
     */
    String[] loadUserWithUsername(String username) throws UserDoesNotExistException;

    /**
     * Save the user information to the database.
     * @param username - username of the user
     * @param password - password of the user
     * @param name - name of the user
     * @param email - email of the user
     */
    void saveUser(String username, String password, String name, String email);
    void editUser(String key, String change, String username);
}
