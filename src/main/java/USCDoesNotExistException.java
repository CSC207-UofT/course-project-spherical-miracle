public class USCDoesNotExistException extends Exception {

    /**
     * Constructs a new UserScheduleCollection exception.
     * @param username
     */
    public USCDoesNotExistException(String username) {
        super(username);
    }
}
