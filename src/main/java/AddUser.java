import java.util.Arrays;

public class AddUser {

    private String[] user;
    private UserManager manager;


    /**
     * Construct a list of the information needed to create a new user and the UserManager data.
     *
     * @param username string of username.
     * @param password string of password.
     * @param email string of email.
     * @param name string of name.
     * @param manager UserManager object.
     */
    public AddUser(String username, String password, String email, String name, UserManager manager){
        this.manager = manager;
        this.user = new String[]{username, password, email, name};
    }

    /**
     * If the information is valid, add the user to the UserManager object then return true. Otherwise, return false.
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
}
