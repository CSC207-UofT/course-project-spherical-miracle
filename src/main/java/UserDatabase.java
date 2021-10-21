import java.util.HashMap;

public class UserDatabase {
    //TODO: replace with database

    //Usernames must be unique
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
    public void save(User user) {
        userMap.put(user.getUsername(), user);
    }

    /**
     * Removes user from this database.
     * @param username
     * @return User object with this username
     */
    public User remove(String username) {
        return userMap.remove(username);
    }

    /**
     * Return User with given username.
     * @param username
     * @return
     */
    public User getUserWithUsername(String username) {
        if (this.hasUserWithUsername(username))
            return userMap.get(username);
        return null; // MAKE THIS AN EXCEPTION?
    }

    /**
     * Returns true iff there is a user in this database with username.
     * @param username
     * @return
     */
    public boolean hasUserWithUsername(String username) {
        return userMap.containsKey(username);
    }
}

