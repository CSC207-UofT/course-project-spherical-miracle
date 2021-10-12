import java.util.HashMap;

public class UserManager {
    //TODO: replace with database
    public HashMap<String, User> userMap;

    /**
     * Construct a userMap given a HashMap.
     *
     * @param userMap HashMap of users.
     */
    public UserManager(HashMap<String, User> userMap){
        this.userMap = userMap;
    }

    /**
     * Construct a new userMap.
     *
     *
     */
    public UserManager(){
        this.userMap = new HashMap<>();
    }
}
