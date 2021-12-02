import Schedule.Entities.Meal;
import Schedule.Entities.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractScheduleEntityTest {

    private Meal meal1;
    private Meal meal2;
    private Meal meal3;
    private Workout workout1;
    private Workout workout2;
    private Workout workout3;
    @BeforeEach
    void setUp() {
        meal1 = new Meal("Sugar", 4);
        meal2 = new Meal("Burger", 9999);
        meal3 = new Meal("Salt", 0);
        workout1 = new Workout("Bicep Curl", 100);
        workout2 = new Workout("Relaxing", 0);
        workout3 = new Workout("Running", 99999);

    }

    @Test
    void getName() {
        assert meal1.getName().equals("Sugar");
        assert meal2.getName().equals("Burger");
        assert meal3.getName().equals("Salt");
        assert workout1.getName().equals("Bicep Curl");
        assert workout2.getName().equals("Relaxing");
        assert workout3.getName().equals("Running");
    }

    @Test
    void getCalories() {
        assert meal1.getCalories() == 4;
        assert meal2.getCalories() == 9999;
        assert meal3.getCalories() == 0;
        assert workout1.getCalories() == 100;
        assert workout2.getCalories() == 0;
        assert workout3.getCalories() == 99999;
    }
}