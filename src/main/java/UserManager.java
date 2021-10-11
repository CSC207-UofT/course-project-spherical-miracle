import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> userMap;

    /**
     * Construct a UserMap given a HashMap.
     *
     * @param userMap HashMap of users.
     */
    public UserManager(HashMap<String, User> userMap){
        this.userMap = userMap;
    }

    /**
     * Construct a new UserMap.
     *
     *
     */
    public UserManager(){
        this.userMap = new HashMap<>();
    }

    /**
     * Return the User based on username.
     *
     * @return User
     **/
    public User getUser(String username){ return userMap.get(username); }

    /**
     * Add a new user to the HashMap.
     *
     *
     **/
    public void addUser(User newUser){ userMap.put(newUser.getUsername(), newUser);}


}
