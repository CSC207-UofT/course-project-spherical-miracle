public class FetchUserUseCase {

    private final DataAccessInterface database;

    public FetchUserUseCase(DataAccessInterface database) {
        this.database = database;
    }

    public User getUser(String username) {
        try {
            String[] userInfo = database.findUser(username);
            User user = new User(userInfo[0], userInfo[1], userInfo[2] ,userInfo[3]);
            return user;
        } catch (UserDoesNotExistException e) {
            return null;
        }
    }
}
