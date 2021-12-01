import Schedule.Entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    private Meal meal1;
    private Meal meal2;
    private Meal meal3;
    @BeforeEach
    void setUp() {
        meal1 = new Meal("Sugar", 4);
        meal2 = new Meal("Burger", 9999);
        meal3 = new Meal("Salt", 0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assert meal1.getName().equals("Sugar");
        assert meal2.getName().equals("Burger");
        assert meal3.getName().equals("Salt");
    }

    @Test
    void getCalories() {
        assert meal1.getCalories() == 4;
        assert meal2.getCalories() == 9999;
        assert meal3.getCalories() == 0;
    }
}