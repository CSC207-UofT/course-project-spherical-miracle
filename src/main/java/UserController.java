import java.util.Arrays;
/**
 * A controller that delegates actions for managing a database of users.
 */
public class UserController {

    /**
     * A database of users to manage.
     */
    // Does Database being here violate CleanArch?
    private final UserDatabase users;

    /**
    * Construct a list of the information needed to create a new user and the UserDatabase data.
    * @param users UserDatabase object.
    */
    public UserController(UserDatabase users) {
        this.users = users;
    }

    /**
     * If the information is valid, add the user to the UserDatabase object then return true.
     * Otherwise, return false.
     * @param username the user's username
     * @param password the user's password
     * @param name the user's name
     * @param email the user's email
     * @return whether the user's info was valid and the user was added to the database or not
     **/
    public boolean addUser(String username, String password, String name, String email) {
        if (!userInfoIsValid(username, password, name, email))
            return false;
        CreateUserInputBoundary createUserInputBoundary = new CreateUserUseCase(users);
        boolean result = createUserInputBoundary.createUser(username, password, name, email);
        if (result)
            System.out.println("User added!");
        else
            System.out.println("Failed to create user. (insert reason here?)");
        return result;
    }

    /**
     * Remove the user from the UserDatabase.
     * @param username the user's username
     */
    public void removeUser(String username) {
        //TODO: validating inputs
        users.remove(username);
    }

    /**
     * Return true iff information is valid.
     * @param username the user's username
     * @param password the user's password
     * @param name the user's name
     * @param email the user's email
     * @return whether or not the user's information is valid or not
     */
    private boolean userInfoIsValid(String username, String password, String name, String email) {
        String[] userInfo = {username, password, name, email};
        for (String info : userInfo) {
            // Could add more checks
            if (info.isBlank())
                return false;
        }
        return true;
    }
}
