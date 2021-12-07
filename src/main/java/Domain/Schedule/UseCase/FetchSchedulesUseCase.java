package Domain.Schedule.UseCase;

import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Domain.Schedule.Entities.Day;
import Domain.Schedule.Entities.Meal;
import Domain.Schedule.Entities.Schedule;
import Domain.Schedule.Entities.Workout;
import Database.ScheduleDataAccess;
import Database.ScheduleDataAccess.ScheduleInfo;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A use case that fetches Schedules from the database.
 */
public class FetchSchedulesUseCase {
    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;

    /**
     * Constructs a use case that can fetch lists of schedules.
     * @param databaseInterface - the database gateway
     * @param outputBoundary - the output boundary
     */
    public FetchSchedulesUseCase(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Returns the active Schedule associated with the given username.
     * @param username - the username of the user
     * @return active schedule associated with the given username
     */
    public Schedule getActiveSchedule(String username){
        ScheduleDataAccess.ScheduleInfo schedule = databaseInterface.loadActiveSchedule(username);
        if (schedule == null){
            return null;
        }
        return stringToSchedule(schedule.getId(), schedule.getName(), schedule.getDetails());
    }

    /**
     * Creates a list of Schedule IDs from a list of Schedules.
     * @param schedules list of Schedules
     * @return list of Schedule IDs
     */
    private List<String> schedulesToScheduleIDs(List<Schedule> schedules) {
        List<String> schedulesIDs = new ArrayList<>();
        for (Schedule schedule: schedules) {
            schedulesIDs.add(schedule.getId());
        }
        return schedulesIDs;
    }

    /**
     * Returns a list of IDs for the schedules associated with the given username.
     * @param username - the username of the user.
     * @return list of IDs for the schedules associated with the given username.
     */
    public List<String> getScheduleAssociatedWith(String username) {
        List<Schedule> schedules = new ArrayList<>();
        List<ScheduleDataAccess.ScheduleInfo> schedulesInfos = databaseInterface.loadSchedulesFor(username);
        List<String> scheduleNames = new ArrayList<>();
        ScheduleDataAccess.ScheduleInfo activeInfo = databaseInterface.loadActiveSchedule(username);
        for (ScheduleDataAccess.ScheduleInfo scheduleString: schedulesInfos) {
            schedules.add(stringToSchedule(scheduleString.getId(), scheduleString.getName(), scheduleString.getDetails()));
            scheduleNames.add(scheduleString.getName());
        }
        outputBoundary.listSchedules(scheduleNames);
        if (activeInfo == null){
            outputBoundary.noActiveSchedule();
        } else {
            outputBoundary.currentActiveSchedule(activeInfo.getName());
        }
        return schedulesToScheduleIDs(schedules);
    }

    /**
     * Returns the list of public schedules.
     * @return list of public schedules.
     */
    public List<String> getPublicSchedules() {
        List<Schedule> publicSchedules = new ArrayList<>();
        List<ScheduleInfo> scheduleInfos = databaseInterface.loadPublicSchedules();
        List<String> scheduleNames = new ArrayList<>();
        for (ScheduleInfo scheduleString: scheduleInfos) {
           publicSchedules.add(stringToSchedule(scheduleString.getId(), scheduleString.getName(), scheduleString.getDetails()));
           scheduleNames.add(scheduleString.getName());
        }
        outputBoundary.listSchedules(scheduleNames);
        return schedulesToScheduleIDs(publicSchedules);
    }

    /**
     * Converts string of schedule from database into a Schedule object
     *
     * @param id UUID of schedule
     * @param name string representation of schedule from dat
     * @param scheduleInfo list of information for Schedule entity
     * @return a Schedule object
     */
    private Schedule stringToSchedule(String id, String name, List<List<List<Map<String, String>>>> scheduleInfo) {
        Schedule s = new Schedule(name, id);
        for (List<List<Map<String, String>>> day: scheduleInfo) {
            Day d = new Day();

            for (Map<String, String> workout: day.get(0)) {
                Workout w = new Workout(workout.get(databaseInterface.workoutName),
                                        Integer.parseInt(workout.get(databaseInterface.calories)));
                d.addWorkout(w);
            }

            for (Map<String, String> meal: day.get(1)) {
                Meal m = new Meal(meal.get(databaseInterface.mealName),
                                  Integer.parseInt(meal.get(databaseInterface.calories)));
                d.addMeal(m);
            }

            s.setDay(DayOfWeek.of(scheduleInfo.indexOf(day)+1), d);// index of day + 1 because set day does -1
        }
        return s;
    }

    /**
     * Gets a schedule from a schedule ID
     * @param scheduleID String of Schedule ID
     * @return Schedule corresponding with the ID
     */
    public Schedule getScheduleWithID(String scheduleID) {
        ScheduleInfo s = databaseInterface.loadScheduleWithID(scheduleID);
        return stringToSchedule(s.getId(), s.getName(), s.getDetails());
    }
}
