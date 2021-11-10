package Schedule;

/**
 * A Schedule.Meal.
 */

public class Meal {

    /**
     * The name of the meal.
     */
    private String name;

    /**
     * The calories for the meal.
     */
    private int calories;
    //TODO: add macros? (keep this for last)

    /**
     * Construct a Schedule.Meal, giving them the name of the meal
     * and the calories in it.
     * @param name name of the Schedule.Meal
     * @param calories the caloric intake of the meal
     */
    public Meal(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    /**
     * Return the name of this Schedule.Meal.
     * @return meal name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the caloric intake of this Schedule.Meal.
     * @return the calories in the meal
     */
    public int getCalories() {
        return calories;
    }
}