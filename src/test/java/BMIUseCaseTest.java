import Adapters.Presenter;
import Domain.User.UseCase.AddHeightWeightUseCase;
import Domain.User.UseCase.BMIUseCase;
import Domain.User.UseCase.FetchUserUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

public class BMIUseCaseTest {
    private MockDatabase mock = new MockDatabase();
    private FetchUserUseCase fetch = new FetchUserUseCase(mock);
    private BMIUseCase bmi;
    private String username = "username1";
    private Presenter presenter = new Presenter();


    @BeforeEach
    void setUp() {
        bmi = new BMIUseCase(fetch, presenter);
        mock.saveUser(username, "", "", "");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void userExists() {
    assert bmi.BMIMessage("username1");
    }

    @Test
    void userNotExists() {
        assert !bmi.BMIMessage("username");
    }
}
