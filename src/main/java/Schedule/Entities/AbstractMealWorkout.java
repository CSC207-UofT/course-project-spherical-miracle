package Schedule.Entities;

abstract class AbstractMealWorkout implements FactoryMealWorkout{
    private final String name;
    private final int calories;

    public AbstractMealWorkout(String name, int calories){
        this.name = name;
        this.calories = calories;
    }

    /**
     * Return the name of this item.
     * @return item name
     */
    public String getName() {return name;}

    /**
     * Return the amount of calories for this item.
     * @return the calories of the item
     */
    public int getCalories() {return calories;}

}
