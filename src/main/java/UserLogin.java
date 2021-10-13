import java.util.HashMap;

public class UserLogin {
    private HashMap<String, User> userMap;

    /**
     * Construct a userMap given a HashMap.
     *
     * @param userMap HashMap of users.
     */
    public UserLogin(UserDatabase manager){
        this.userMap = manager.userMap;
    }

    /**
     * Return the User based on username.
     *
     * @return User
     **/
    public boolean CheckUser(String username, String password){
        if (userMap.containsKey(username)) {
            return password == userMap.get(username).getPassword();
        }
        return false;
    }

}
