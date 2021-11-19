import User.User;

import java.util.HashMap;
/**
 * Stores the corresponding information for each user's account.
 */
public class UserDatabase {
    //TODO: replace with database

    /**
     * A HashMap where User.User objects correspond to unique usernames.
     */
    private HashMap<String, User> userMap;

    /**
     * Constructs a userMap given a HashMap.
     * @param userMap the HashMap of users
     */
    public UserDatabase(HashMap<String, User> userMap){
        this.userMap = userMap;
    }

    /**
     * Constructs a new userMap.
     */
    public UserDatabase(){
        this.userMap = new HashMap<>();
    }

    /**
     * Saves user to this database.
     * @param user the specific User.User object to be saved
     */
    public void save(User user) {
        userMap.put(user.getUsername(), user);
    }

    /**
     * Removes user from this database.
     * @param username the username of the user to be removed
     * @return User.User object with this username
     */
    public User remove(String username) {
        return userMap.remove(username);
    }

    /**
     * Return User.User with given username.
     * @param username username of the user of interest
     * @return the User.User object with the corresponding username
     */
    public User getUserWithUsername(String username) {
        if (this.hasUserWithUsername(username))
            return userMap.get(username);
        return null; // MAKE THIS AN EXCEPTION?
    }

    /**
     * Returns true iff there is a user in this database with username.
     * @param username username of the user being looked up
     * @return a boolean detailing if the username is already in the database
     */
    public boolean hasUserWithUsername(String username) {
        return userMap.containsKey(username);
    }
}

