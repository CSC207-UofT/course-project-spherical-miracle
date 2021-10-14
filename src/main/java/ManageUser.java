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
    public boolean addUser() {
        //TODO: validating inputs
        boolean invalid = Arrays.stream(user).anyMatch(n -> (n == null));

        if (!invalid) {
            User new_User = new User(user[0], user[1], user[2], user[3]);
            manager.getUserMap().put(user[0], new_User);
            return true;
        }
        return false;
    }
    public void removeUser(String username) {
        //TODO: validating inputs
        manager.getUserMap().remove(username);
    }
}
