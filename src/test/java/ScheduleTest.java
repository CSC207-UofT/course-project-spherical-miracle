import Schedule.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScheduleTest {
    Schedule week1;

    @BeforeEach
    void setUp() {
        week1 = new Schedule("First week!");
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void getName(){
        assert week1.getName().equals("First week!");
    }

    @Test
    void setName(){
        week1.setName("actually this is the second week");
        assert week1.getName().equals("actually this is the second week");
    }

    @Test
    void addWorkout() {
        week1.addWorkout(1, new Workout("ass", 500));
    }


}
