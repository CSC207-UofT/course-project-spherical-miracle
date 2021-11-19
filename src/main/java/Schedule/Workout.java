package Schedule;

/**
 * A workout session.
 */

public class Workout {

    private String name;

    private int caloriesBurnt;
    //TODO: add duration?

    /**
     * Constructs a Workout with the given name and amount of calories burnt
     * @param name name of the Workout
     * @param caloriesBurnt the amount of calories burnt for the workout
     */
    public Workout(String name, int caloriesBurnt) {
        this.name = name;
        this.caloriesBurnt = caloriesBurnt;
    }

    /**
     * Return the name of this Workout.
     * @return workout name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the amount of calories burnt for this Workout.Workout.
     * @return the calories burnt
     */
    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }
}