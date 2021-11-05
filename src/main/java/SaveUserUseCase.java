public class SaveUserUseCase {

    private final UserDataAccess database;

    public SaveUserUseCase(UserDataAccess database) {
        this.database = database;
    }

    public void saveUser(User user) {
        database.saveUser(user.getUsername(), user.getPassword(), user.getName(), user.getEmail());
    }
}
