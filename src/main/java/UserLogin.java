import java.util.HashMap;

public class UserLogin {
    private HashMap<String, User> userMap;

    /**
     * Construct a userMap given a HashMap.
     *
     * @param manager HashMap of users.
     */
    public UserLogin(UserDatabase manager){
        this.userMap = manager.getUserMap();
    }

    /**
     * Return the User based on username.
     *
     * @return User
     **/
    public boolean CheckUser(String username, String password){
        if (userMap.containsKey(username)) {
            return password.equals(userMap.get(username).getPassword());
        }
        return false;
    }

}