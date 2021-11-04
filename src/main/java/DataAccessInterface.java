public interface DataAccessInterface {
    String[] findUser(String username) throws UserDoesNotExistException;
    String[] loadSchedule();
    void saveUser(String username, String password, String name, String email);
    void saveSchedule();
}
