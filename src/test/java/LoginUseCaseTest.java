import Domain.User.Entities.User;
import Domain.User.UseCase.FetchUserUseCase;
import Domain.User.UseCase.LoginUseCase;
import Domain.User.UseCase.UserDoesNotExistException;
import Domain.User.Boundary.LoginResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginUseCaseTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() throws UserDoesNotExistException {
        String username = "Jacob123";
        String nonexistentUsername = "Asif";
        String expectedPassword = "Matrix";
        String wrongPassword = "Vector";
        MockDatabase db = new MockDatabase();
        db.saveUser(username, expectedPassword, "Jacob", "Jacob@mail.uk");
        FetchUserUseCase fetch = new FetchUserUseCase(db);
        LoginUseCase login = new LoginUseCase();
        User u1 = fetch.getUser(username);
        assert login.login(u1, expectedPassword) == LoginResult.SUCCESS;
        assert login.login(u1, wrongPassword) == LoginResult.INCORRECT_PASSWORD;
    }
}