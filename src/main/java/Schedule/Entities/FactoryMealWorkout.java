package Schedule.Entities;

/**
 * Design pattern for Meal and Workout entity
 */

public interface FactoryMealWorkout {
    /**
     * Returns name of the item
     * @return name of the item
     */
    String getName();
    int getCalories();
}