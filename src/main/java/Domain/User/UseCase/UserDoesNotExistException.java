package Domain.User.UseCase;

/**
 * A special exception for when a user with the given username does not exist.
 */
public class UserDoesNotExistException extends Exception {

    /**
     * Constructs a UserDoesNotExistException with an argument specifying the username that yielded no users.
     * @param username - the username that yielded no users
     */
    public UserDoesNotExistException(String username) {
        super("There is no existing user with username:" + username);
    }
}

