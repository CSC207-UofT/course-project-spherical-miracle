import java.util.Arrays;

public class ManageUser {

    private final UserDatabase users;


    /**
     * Construct a list of the information needed to create a new user and the UserDatabase data.
     *
     * @param users UserDatabase object.
     */
    public ManageUser(UserDatabase users) {
        this.users = users;
    }

    /**
     * If the information is valid, add the user to the UserDatabase object then return true. Otherwise, return false.
     *
     * @return boolean
     **/
    public boolean addUser(String username, String password, String name, String email) {
        //TODO: validating inputs
        if (!userInfoIsValid(username, password, name, email))
            return false;
        User user = new User(username, password, name, email);
        // TODO: eventually line 35 will be done through an interface?
        users.save(user);
        return true;
    }

    public void removeUser(String username) {
        //TODO: validating inputs
        users.remove(username);
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