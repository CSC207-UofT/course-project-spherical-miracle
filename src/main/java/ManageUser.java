import java.util.Arrays;

public class ManageUser {

    private String[] user;
    private UserDatabase manager;


    /**
     * Construct a list of the information needed to create a new user and the UserDatabase data.
     *
     * @param username string of username.
     * @param password string of password.
     * @param email string of email.
     * @param name string of name.
     * @param manager UserDatabase object.
     */
    public ManageUser(String username, String password, String email, String name, UserDatabase manager){
        this.manager = manager;
        this.user = new String[]{username, password, email, name};
    }

    /**
     * If the information is valid, add the user to the UserDatabase object then return true. Otherwise, return false.
     *
     * @return boolean
     **/
    public boolean AddUser() {
        //TODO: validating inputs
        boolean is_valid = Arrays.stream(user).anyMatch(n -> (n == null));
        if (is_valid) {
            User new_User = new User(user[0], user[1], user[2], user[3]);
            manager.userMap.put(user[0], new_User);
            return true;
        }
        return false;
    }
    public void RemoveUser(String username) {
        //TODO: validating inputs
        manager.userMap.remove(username);
    }
}
