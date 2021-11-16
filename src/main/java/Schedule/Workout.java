package Schedule;

/**
 * A workout session.
 */

public class Workout {

    /**
     * The name of the workout.
     */
    private String name;

    /**
     * The calories burnt for this workout.
     */
    private int caloriesBurnt;
    //TODO: add duration?

    /**
     * Construct a Workout.Workout, giving them the name of the workout
     * and the caloriesBurnt.
     * @param name name of the Workout.Workout
     * @param caloriesBurnt the amount of calories burnt for the workout
     */
    public Workout(String name, int caloriesBurnt) {
        this.name = name;
        this.caloriesBurnt = caloriesBurnt;
    }

    /**
     * Return the name of this Workout.Workout.
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