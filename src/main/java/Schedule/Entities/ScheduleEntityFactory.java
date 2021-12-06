package Schedule.Entities;

/**
 * A factory that produces schedule entities
 * currently available schedule entities:
 * - Meal
 * - Workout
 */

public class ScheduleEntityFactory {

    /**
     * ScheduleEntity Factory which returns a new schedule entity based arguments given
     *
     * @param entityType the type of schedule entity desired
     * @param name the name for a specific instance of the schedule entity
     * @param calories the calories for a specific instance of the schedule entity
     * @returns ScheduleEntity
     */
    public ScheduleEntity getScheduleEntity(String entityType, String name, int calories){
        if (entityType.equalsIgnoreCase("m")) {
            return new Meal(name, calories);
        } else {
            return new Workout(name, calories);
        }
    }
}
