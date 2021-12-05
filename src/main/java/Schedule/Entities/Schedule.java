package Schedule.Entities;

import java.time.DayOfWeek;
import java.util.UUID;

/**
 * A seven-day schedule for a user to customise.
 */
public class Schedule {

    private String name;
    private UUID id;

    private Day[] plan = new Day[7];

    /**
     * Constructs a Schedule with the specified name.
     * @param name name of the Schedule
     */
    public Schedule(String name) {
        this(name, UUID.randomUUID().toString());
    }
    /**
     * Construct a Schedule with the specified name and id.
     * @param name name of the Schedule
     * @param id unique id of the Schedule
     */
    public Schedule(String name, String id) {
        this.name = name;
        this.id = UUID.fromString(id);
        for (int i = 0; i != plan.length; i++) {
            plan[i] = new Day();
        }
    }


    /**
     * Returns the name of this Schedule.
     * @return name of this Schedule.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unique identifier of this schedule.
     * @return the unique identifier of this schedule.
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Sets the name of the schedule to be the given name.
     * @param name - the specified new name of this schedule
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a given day into the schedule.
     * @param dayOfWeek the days of the week from 0 being Sunday to 6 being Saturday
     * @param day the day being added
     */
    public void setDay(DayOfWeek dayOfWeek, Day day) {
        plan[dayOfWeek.getValue() - 1] = day;
    }

    /**
     * Remove the day from the list of days for a specific date.
     * @param date the specified day
     * Precondition: the given date exists in this current schedule
     */
    public void cancelDay(int date) { plan[date] = null; }

    /**
     * Return the workout plan for a specific day.
     * @param dayOfWeek the specific day
     * @return a Day
     */
    public Day getDay(DayOfWeek dayOfWeek) {
        return plan[dayOfWeek.getValue() - 1];
    }

    /**
     * Print a string representation of a user's specific schedule.
     * @return string representation of schedule
     */
    @Override
    public String toString(){
        StringBuilder outputMsg = new StringBuilder();
        for (DayOfWeek c: DayOfWeek.values()){
            outputMsg.append(this.getDay(c).printDay(c.getValue()));
        }
        return outputMsg.toString();
    }
}