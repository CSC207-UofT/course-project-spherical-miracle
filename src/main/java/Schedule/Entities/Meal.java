package Schedule.Entities;

/**
 * A Meal.
 */

public class Meal {

    private String name;
    private int calories;
    //TODO: add macros? (keep this for last)

    /**
     * Constructs a Meal with the given name and the calories in it.
     * @param name name of the Meal
     * @param calories the caloric intake of the meal
     */
    public Meal(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    /**
     * Returns the name of this Meal.
     * @return meal name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the caloric intake of this Meal.
     * @return the calories in this meal
     */
    public int getCalories() {
        return calories;
    }
}