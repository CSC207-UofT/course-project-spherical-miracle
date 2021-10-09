public class Workout {

    private String name;
    //TODO: caloriesBurnt, calBurnt?
    private int caloriesBurnt;
    //TODO: add duration?

    /**
     * Construct an Workout, giving them the name of the workout
     * and the caloriesBurnt.
     *
     * @param name name of the Workout
     * @param caloriesBurnt the amount of calories burnt for the workout
     */

    public Workout(String name, int caloriesBurnt) {
        this.name = name;
        this.caloriesBurnt = caloriesBurnt;
    }

    /**
     * Return the name of this Workout.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return the amount of calories burnt for this Workout.
     * @return
     */
    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }


}