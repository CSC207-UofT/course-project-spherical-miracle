package Domain.Schedule.Entities;

/**
 * A workout session.
 */
public class Workout extends AbstractScheduleEntity implements ScheduleEntity{
    /**
     * Constructs a Workout with the given name and amount of calories burnt
     * @param name name of the Workout
     * @param caloriesBurnt the amount of calories burnt for the workout
     */
    public Workout(String name, int caloriesBurnt) {
        super(name, caloriesBurnt);
    }
}