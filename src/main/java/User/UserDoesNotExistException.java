package User;

public class UserDoesNotExistException extends Exception {

    /**
     * Constructs a UserDoesNotExistException with an argument specifying the username that yielded no users.
     * @param username - the username that yielded no users
     */
    public UserDoesNotExistException(String username) {
        super("There is no existing user with username:" + username);
    }
}

