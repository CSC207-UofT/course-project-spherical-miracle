import Adapters.Presenter;
import Database.UserDataAccess;
import Domain.User.Entities.User;
import Domain.User.UseCase.CreateUserUseCase;
import Domain.User.UseCase.UserDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.Assert.assertThrows;

class CreateUserUseCaseTest {

    private MockDatabase db = new MockDatabase();
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
        CreateUserUseCase create = new CreateUserUseCase(db, new Presenter());
        create.createUser(expectedUsername, expectedPassword, expectedName, expectedEmail);
        UserDataAccess.UserInfo userinfo = db.loadUserWithUsername(expectedUsername);
        db.saveUser(expectedUsername, expectedPassword, expectedName, expectedEmail);
        assert userinfo.getUsername().equals(expectedUsername);
        assert BCrypt.checkpw(expectedPassword, userinfo.getPassword());
        assert userinfo.getName().equals(expectedName);
        assert userinfo.getEmail().equals(expectedEmail);
    }

    @Test
    void noUser() {
        String unexpectedUsername = "YourMom";
        UserDoesNotExistException thrown = assertThrows(UserDoesNotExistException.class,
                () -> db.loadUserWithUsername(unexpectedUsername));
    }
}