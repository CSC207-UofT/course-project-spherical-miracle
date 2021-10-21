import java.util.HashMap;

public class UserLogin implements LoginInputBoundary {
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
    public boolean CheckUser(String username, String password) {
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        if (userMap.containsKey(username)) {
            userMap.get(username).passwordMatches(password);
        }
        return false;
    }
}
