public class USCDoesNotExistException extends Exception {
    public USCDoesNotExistException(String username) {
        super(username);
    }
}
