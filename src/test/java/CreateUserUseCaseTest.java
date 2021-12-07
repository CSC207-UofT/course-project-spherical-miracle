import User.UseCase.CreateUserUseCase;
import User.UseCase.UserDoesNotExistException;
import User.Entities.User;
import Adapters.Presenter;
import Database.UserDataAccess.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThrows;

class CreateUserUseCaseTest {

    private final MockDatabase database = new MockDatabase();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser() throws UserDoesNotExistException {
        String expectedUsername = "PresidentUofT";
        String expectedPassword = "438892.04";
        String expectedName = "Meric Gertler";
        String expectedEmail = "president@utoronto.ca";
        CreateUserUseCase create = new CreateUserUseCase(database, new Presenter());
        create.createUser(expectedUsername, expectedPassword, expectedName, expectedEmail);
        UserInfo userinfo = database.loadUserWithUsername(expectedUsername);
        User user = new User(expectedUsername, expectedPassword, expectedName, expectedEmail);
        assert userinfo.getUsername().equals(expectedUsername);
        // TODO: Look up how to do assertion with hashed passwords
        // assert BCrypt.hashpw(expectedPassword, BCrypt.gensalt(10)).equals(userinfo.getPassword());
        assert userinfo.getName().equals(expectedName);
        assert userinfo.getEmail().equals(expectedEmail);

    }

    @Test
    void noUser() {
        String unexpectedUsername = "YourMom";
        UserDoesNotExistException thrown = assertThrows(UserDoesNotExistException.class,
                () -> database.loadUserWithUsername(unexpectedUsername));
    }
}