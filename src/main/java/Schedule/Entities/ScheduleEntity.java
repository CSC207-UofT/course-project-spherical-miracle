package Schedule.Entities;

/**
 * Design pattern for Meal and Workout entity
 */

public interface ScheduleEntity {
    /**
     * Returns name of the item
     * @return name of the item
     */
    String getName();
    int getCalories();
}