//import statements go here
import java.util.ArrayList;

/**
 * A general day in the user's schedule that is not tied to a particular weekday.
 */

// such that some other class attaches the specific weekday to a day object?
// need a method to store the data of the workouts (possibly multiple for one day)
// order matters (true)
// size (number of workouts to keep track of for a day)
// may need to change dynamically --> look to arraylist?
// or hard code a hard cap ex. can only do 5 different types of workouts per day (this might be a good idea
// if someone tries to put too many workouts in one day)

// will need to also update Scheduler based on new structure of storing workouts

// for calculating calories burnt per workout, should we sum them up now?
// may be using in calculating calorie intake vs outtake
// when implementing meals + be a part of the daily summaries
// how should we represent meals? just the name with the calories it has?

public class Day {
    private Workout[] workouts;
    private ArrayList<String> meals;
    private int intake;
    private int outtake;

    /**
     * Construct a Day
     */
    public Day(){
        workouts = new Workout[5];
        meals = new ArrayList<>();
        intake = 0;
        outtake = 0;
    }

    /**
     * Add a workout to the list of workouts and return true as long as the list isn't already filled.
     * @param workout workout to be added for the day
     * @return if adding the workout was successful or not
     */
    public boolean addWorkout(Workout workout){
        int i = 0;
        while ((workouts[i] != null) && (i < 5)){
            i = i + 1;
        }
        if (i < 5){
            workouts[i] = workout;
            calBurnt = calBurnt + workout.getCaloriesBurnt();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Remove a workout from the list of workouts and return true as long as the workout was removed.
     * @param workout workout to be removed from day
     * @return if removing the workout was successful or not
     */
    public boolean removeWorkout(Workout workout){
        for (int i = 0; i < 5; i++){
            if (workouts[i].getName().equals(workout.getName())){
                calBurnt = calBurnt - workout.getCaloriesBurnt();
                return true;
            }
        }
        return false;
    }

    /**
     * Return the net calories burnt for the day.
     */
    public int getCalories(){ return intake - calBurnt; }

    /**
     * Return the absolute calories burnt for the day.
     */
    public int getOuttake(){ return calBurnt; }

    /**
     * Return the intake of calories for the day.
     */
    public int getIntake(){ return intake; }

    /**
     * Return a string representation of the day.
     */
    public String getDay(){
        StringBuilder stringWorkouts = new StringBuilder();
        for(Workout workout: workouts){
            if (!(workout == null)){
                stringWorkouts.append(workout.getName());
                stringWorkouts.append(", ");
            }
        }

//        StringBuilder stringMeals = new StringBuilder();
//        for(Meal meal: meals){
//            stringMeals.append(meal);
//            stringMeals.append(", ");
//        }
        //
        stringWorkouts = new StringBuilder(stringWorkouts.substring(0, stringWorkouts.length() - 2));
        return stringWorkouts.toString();
    }
}
