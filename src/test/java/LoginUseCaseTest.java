import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class LoginUseCaseTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        String username = "Jacob123";
        String nonexistentUsername = "Asif";
        String expectedPassword = "Matrix";
        String wrongPassword = "Vector";
        MockDatabase db = new MockDatabase();
        db.saveUser(username, expectedPassword, "Jacob", "Jacob@mail.uk");
        FetchUserUseCase fetch = new FetchUserUseCase(db);
        LoginUseCase login = new LoginUseCase(fetch);
        assert login.login(username, expectedPassword) == LoginUseCase.LoginResult.SUCCESS;
        assert login.login(username, wrongPassword) == LoginUseCase.LoginResult.INCORRECT_PASSWORD;
        assert login.login(nonexistentUsername, expectedPassword) == LoginUseCase.LoginResult.NO_SUCH_USER;
    }
}