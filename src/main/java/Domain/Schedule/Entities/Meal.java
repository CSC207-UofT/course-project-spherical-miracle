package Domain.Schedule.Entities;

/**
 * A Meal.
 */

public class Meal extends AbstractScheduleEntity implements ScheduleEntity{

    /**
     * Constructs a Meal with the given name and the calories in it.
     * @param name name of the Meal
     * @param calories the caloric intake of the meal
     */
    public Meal(String name, int calories) {
        super(name, calories);
    }
}