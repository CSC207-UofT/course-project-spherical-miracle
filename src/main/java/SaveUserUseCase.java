public class SaveUserUseCase {

    private final DataAccessInterface database;

    public SaveUserUseCase(DataAccessInterface database) {
        this.database = database;
    }

    public void saveUser(User user) {
        database.saveUser(user.getUsername(), user.getPassword(), user.getName(), user.getEmail());
    }
}
