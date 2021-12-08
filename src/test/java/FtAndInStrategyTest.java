import Domain.User.ConvertStrategies.HeightStrategies.FtAndInStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FtAndInStrategyTest {
    private FtAndInStrategy strategy = new FtAndInStrategy();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void zeroCm() {
        assert strategy.getM(0) == 0;
    }

    @Test
    void positiveCm() {
        assert strategy.getM(3.2) == 0.975312404754648;
    }
}
