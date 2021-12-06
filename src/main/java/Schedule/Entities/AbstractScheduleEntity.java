package Schedule.Entities;

/**
 * An abstract class for everytype of schedule entity/item
 * Currently this includes:
 * - Meal
 * - Workout
 */

abstract class AbstractScheduleEntity implements ScheduleEntity {
    private String name;
    private int calories;

    public AbstractScheduleEntity(String name, int calories){
        this.name = name;
        this.calories = calories;
    }

    /**
     * Return the name of this schedule item.
     * @return schedule item name
     */
    public String getName() {return name;}

    /**
     * Return the amount of calories for this schedule item.
     * @return the calories of the schedule item
     */
    public int getCalories() {return calories;}

}
