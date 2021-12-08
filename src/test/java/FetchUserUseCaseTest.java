import Adapters.Presenter;
import Domain.User.Entities.User;
import Domain.User.UseCase.BMIUseCase;
import Domain.User.UseCase.FetchUserUseCase;
import Domain.User.UseCase.UserDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FetchUserUseCaseTest {

    private MockDatabase mock = new MockDatabase();
    private FetchUserUseCase fetch = new FetchUserUseCase(mock);
    private String username = "username1";


    @BeforeEach
    void setUp() {
        mock.saveUser(username, "pass", "user name1", "username@username.ca");
        mock.saveUser("username2", "pass2", "user name2", "username2@username.ca");
        mock.addHeightWeight("username2", 10000, 300);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void userHeightWeight() throws UserDoesNotExistException {
        //TODO This test fails, it is equal to 0. Seems like height is not updating?
        assert fetch.getUser("username2").getHeight() == 10000;
    }

    @Test
    void userNoHeightWeight() throws UserDoesNotExistException {
        assert fetch.getUser("username1").getHeight() == 0;
    }

}
