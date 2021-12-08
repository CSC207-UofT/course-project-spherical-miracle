package Database;

/**
 * A special kind of exception for when a schedule collection does not exist for a user.
 */
public class USCDoesNotExistException extends Exception {

    /**
     * Constructs a new UserScheduleCollection exception.
     * @param username the username associated with the exception
     */
    public USCDoesNotExistException(String username) {
        super(username);
    }
}
