import Adapters.Presenter;
import Domain.User.UseCase.FetchUserUseCase;
import Domain.User.UseCase.LoginUseCase;
import Domain.User.UseCase.LogoutUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogoutUseCaseTest {

    private MockDatabase db = new MockDatabase();
    private FetchUserUseCase fetch = new FetchUserUseCase(db);
    private Presenter p = new Presenter();
    private LogoutUseCase logoutUseCase = new LogoutUseCase(p);
    @BeforeEach
    void setUp() {
        db.saveUser("username", "pass", "user name1", "username@username.ca");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void logout() {
        assert logoutUseCase.logout("username");
    }
}