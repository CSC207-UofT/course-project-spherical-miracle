import java.util.HashMap;

public class UserLogin {
    private final UserDatabase users;
    /**
     * Construct a userMap given a HashMap.
     *
     * @param users HashMap of users.
     */
    public UserLogin(UserDatabase users){
        this.users = users;
    }

    /**
     * Return the User based on username.
     *
     * @return User
     **/
    public boolean CheckUser(String username, String password){
        if (users.hasUserWithUsername(username)) {
            return users.getUserWithUsername(username).passwordMatches(password);
        }
        return false;
    }

}
