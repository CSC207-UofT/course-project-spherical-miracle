package User;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String username) {
        super("There is no existing user with username:" + username);
    }
}

