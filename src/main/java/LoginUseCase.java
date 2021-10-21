import java.util.HashMap;

public class LoginUseCase implements LoginInputBoundary {
    private HashMap<String, User> userMap;

    /**
     * Construct a userMap given a HashMap.
     *
     * @param users HashMap of users.
     */
    public LoginUseCase(UserDatabase users){
        this.userMap = users.getUserMap();
    }

    @Override
    public boolean login(String username, String password) {
        if (userMap.containsKey(username)) {
           return userMap.get(username).passwordMatches(password);
        }
        return false;
    }
}
