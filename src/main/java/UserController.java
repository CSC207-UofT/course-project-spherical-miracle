import java.util.Arrays;

public class UserController {

    private final DataAccessInterface database;


    /**
     * Construct a list of the information needed to create a new user and the UserDatabase data.
     *
     * @param database the use case that handles saving user information.
     */
    public UserController(DataAccessInterface database) {
        this.database = database;
    }

    /**
     * If the information is valid, add the user to the UserDatabase object then return true. Otherwise, return false.
     *;
     * @return boolean
     **/
    public boolean addUser(String username, String password, String name, String email) {
        if (!userInfoIsValid(username, password, name, email))
            return false;
        CreateUserInputBoundary createUserInputBoundary = new CreateUserUseCase(database);
        boolean result = createUserInputBoundary.createUser(username, password, name, email);
        if (result)
            System.out.println("User added!");
        else
            System.out.println("Failed to create user. (insert reason here?)");
        return result;
    }

    public void removeUser(String username) {
        //TODO: validating inputs
        // users.remove(username);
    }

    /**
     * Return true iff information is valid.
     * @param username
     * @param password
     * @param name
     * @param email
     * @return
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
