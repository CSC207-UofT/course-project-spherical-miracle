import User.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateUserUseCaseTest {

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
        MockDatabase db = new MockDatabase();
        CreateUserUseCase create = new CreateUserUseCase(db);
        create.createUser(expectedUsername, expectedPassword, expectedName, expectedEmail);
        String[] userInfo = db.findUser(expectedUsername);
        assert userInfo[0].equals(expectedUsername);
        assert userInfo[1].equals(expectedPassword);
        assert userInfo[2].equals(expectedName);
        assert userInfo[3].equals(expectedEmail);
    }
}