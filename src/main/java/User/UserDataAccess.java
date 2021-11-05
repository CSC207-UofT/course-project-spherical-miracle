package User;

public interface UserDataAccess {
    String[] findUserWithUsername(String username) throws UserDoesNotExistException;
    void saveUser(String username, String password, String name, String email);
    void editUser();
}
