package Schedule.Entities;

public class ScheduleEntityFactory {
    public ScheduleEntity getScheduleEntity(String entityType, String name, int calories){
        if (entityType.equalsIgnoreCase("m")) {
            return new Meal(name, calories);
        } else {
            return new Workout(name, calories);
        }
    }
}
