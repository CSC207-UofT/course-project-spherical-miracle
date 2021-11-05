package User;

public interface UserDataAccess {
    String[] findUser(String username) throws UserDoesNotExistException;
    void saveUser(String username, String password, String name, String email);
    void editUser();
}
