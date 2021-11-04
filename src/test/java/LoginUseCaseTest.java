import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class LoginUseCaseTest {
    class Database implements DataAccessInterface {
        private User user;
        @Override
        public String[] findUser(String username) throws UserDoesNotExistException {
            if (user.getUsername().equals(username)) {
                return new String[] {user.getUsername(), user.getPassword(), user.getName(), user.getEmail()};
            }
            throw new UserDoesNotExistException(username);
        }

        public Database(String username, String password) {
            user = new User(username, password, "name", "email");
        }

        @Override
        public String[] loadSchedule() {
            return new String[0];
        }

        @Override
        public void saveUser(String username, String password, String name, String email) {

        }

        @Override
        public void saveSchedule() {

        }
    }

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
        Database db = new Database(username, expectedPassword);
        FetchUserUseCase fetch = new FetchUserUseCase(db);
        LoginUseCase login = new LoginUseCase(fetch);
        assert login.login(username, expectedPassword) == LoginUseCase.LoginResult.SUCCESS;
        assert login.login(username, wrongPassword) == LoginUseCase.LoginResult.INCORRECT_PASSWORD;
        assert login.login(nonexistentUsername, expectedPassword) == LoginUseCase.LoginResult.NO_SUCH_USER;
    }
}