import Domain.User.ConvertStrategies.HeightStrategies.FtAndInStrategy;
import Domain.User.ConvertStrategies.WeightStrategies.LbsStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LbsStrategyTest {
    private LbsStrategy strategy = new LbsStrategy();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void zeroCm() {
        assert strategy.getKgs(0) == 0;
    }

    @Test
    void positiveCm() {
        assert strategy.getKgs(3.2) == 1.4512471655328798;
    }
}