package Domain.Schedule.Entities;

/**
 * Interface for factory design pattern of schedule entities.
 */

public interface ScheduleEntity {
    /**
     * Returns name of the schedule entity
     * @return name
     */
    String getName();
    /**
     * Returns calories of the schedule entity
     * @return calories
     */
    int getCalories();
}