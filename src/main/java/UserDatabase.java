import java.util.HashMap;

public class UserDatabase {
    //TODO: replace with database
    private HashMap<String, User> userMap;

    /**
     * Construct a userMap given a HashMap.
     *
     * @param userMap HashMap of users.
     */
    public UserDatabase(HashMap<String, User> userMap){
        this.userMap = userMap;
    }

    /**
     * Construct a new userMap.
     *
     *
     */
    public UserDatabase(){
        this.userMap = new HashMap<>();
    }

    /**
     * Saves user to this database.
     */
    public void saveUser(User user) {
        userMap.put(user.getUsername(), user);
    }
    public HashMap<String, User> getUserMap() {
        return userMap;
    }
}
