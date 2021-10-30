import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    private UserDatabase users;
    private SessionController sessionController;

    @BeforeEach
    void setUp() {
         users = new UserDatabase();
         sessionController = new SessionController(users);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        String username = "jacobian";
        String password = "matrix";
        User user = new User(username, password, "jacob", "jacob@gmail.com");
        users.save(user);
        assert sessionController.login(username, password);
        assertFalse(sessionController.login(username, "vector"));
    }

    @Test
    void logout() {
    }
}