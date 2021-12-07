package Database;

public class USCDoesNotExistException extends Exception {

    /**
     * Constructs a new UserScheduleCollection exception.
     * @param username username of User
     */
    public USCDoesNotExistException(String username) {
        super(username);
    }
}
