import Domain.User.ConvertStrategies.HeightStrategies.CmStrategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CmStrategyTest {
    private CmStrategy strategy = new CmStrategy();
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

    void positiveCm() {
        assert strategy.getM(27.3) == 0.273;
    }
}
