import Adapters.Presenter;
import Database.UserDataAccess;
import Database.UserDataAccess.BodyMeasurementRecord;
import Domain.User.UseCase.AddHeightWeightUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;


public class AddHeightWeightUseCaseTest {
    // Doesn't work because entering inputs in methods

    private MockDatabase mock = new MockDatabase();
    private AddHeightWeightUseCase adder;
    private String username = "username1";
    private Presenter presenter = new Presenter();


    @BeforeEach
    void setUp() {
        adder = new AddHeightWeightUseCase(mock, presenter);
        mock.saveUser(username, "", "", "");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add1() {
        Scanner keyboard = new Scanner(System.in);
        ByteArrayInputStream in = new ByteArrayInputStream("cm".getBytes());
        System.setIn(in);

        adder.addHeightWeight("username1");
        BodyMeasurementRecord measurement = mock.getHeightsWeightsFor("username1");
        List<Double> heights = measurement.getHeights();
        List<Double> weights = measurement.getWeights();
        assert heights.get(heights.size() - 1) == 100;
        assert weights.get(weights.size() - 1) == 80;
    }

}
