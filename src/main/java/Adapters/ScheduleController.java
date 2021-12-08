package Adapters;

import java.time.DayOfWeek;
import java.util.List;

import Domain.Schedule.Boundary.CreateScheduleInputBoundary;
import Domain.Schedule.Boundary.RemoveScheduleInputBoundary;
import Domain.Schedule.Boundary.ScheduleOutputBoundary;
import Database.ScheduleDataAccess;
import Domain.Schedule.UseCase.*;

/**
 * A controller that manages a schedule for a user.
 */

public class ScheduleController {

    private final ScheduleDataAccess databaseInterface;
    private final ScheduleOutputBoundary outputBoundary;
    private final FetchSchedulesUseCase fetch;

    /**
     * Construct a list of the information needed to create a new user and the UserDatabase data.
     * @param databaseInterface the dataAccessInterface to the database for schedules.
     */
    public ScheduleController(ScheduleDataAccess databaseInterface, ScheduleOutputBoundary outputBoundary){
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
        fetch = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
    }

    /**
     * Create a new schedule with the given name.
     * Otherwise, return false.
     **/
    public String createSchedule(String scheduleName, String username) {
        CreateScheduleInputBoundary c = new ManageScheduleUseCase(databaseInterface, outputBoundary);
        System.out.println("For each of the 7 days in your schedule, you can have up to five different workouts.");
        return c.createSchedule(scheduleName, username);
    }

    /**
     * View a list of schedule IDS associated with a user.
     * @param username the user that the schedules belong to
     * @return a list of schedule IDs
     **/
    public List<String> viewListOfSchedule(String username){
        return fetch.getScheduleAssociatedWith(username);
    }

    /**
     * Allows the user to choose a schedule from a list, and view, edit, delete, or activate the schedule.
     * @param username username of the user performing the action
     * @param schedulesIDs the list of IDs of schedules to choose from
     */
    public void selectThenViewEditDeleteActivateSchedule(String username, List<String> schedulesIDs) {
        int index = outputBoundary.chooseScheduleFromList(schedulesIDs.size());
        if (schedulesIDs.size() == 0)
            return;
        if (index != -1) {
            while (true) {
                String option = outputBoundary.viewEditDeleteActivateOptions();
                if (option.equalsIgnoreCase("delete")) {
                    RemoveScheduleInputBoundary removeScheduleUseCase = new RemoveScheduleUseCase(databaseInterface, outputBoundary);
                    FetchSchedulesUseCase fetcher = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
                    removeScheduleUseCase.removeSchedule(username, schedulesIDs.get(index), fetcher.getScheduleWithID(schedulesIDs.get(index)));
                } else if (option.equalsIgnoreCase("view")) {
                    DisplayScheduleUseCase display = new DisplayScheduleUseCase(outputBoundary);
                    display.displaySchedule(fetch.getScheduleWithID(schedulesIDs.get(index)));
                }
                else if (option.equalsIgnoreCase("edit")){
                    ManageScheduleUseCase manager = new ManageScheduleUseCase(databaseInterface, outputBoundary);
                    manager.editSchedule(schedulesIDs.get(index), username);
                }
                else if (option.equalsIgnoreCase("activate")){
                    SetActiveScheduleUseCase setActiveScheduleUseCase = new SetActiveScheduleUseCase(databaseInterface, outputBoundary);
                    setActiveScheduleUseCase.setAsActiveSchedule(username, fetch.getScheduleWithID(schedulesIDs.get(index)));
                } else if (option.equalsIgnoreCase("r")){
                    return;
                }
            }
        }
    }

    /**
     * Displays the list of public schedules
     * @param username the user who the public schedules belongs to
     */
    public void viewPublicSchedules(String username) {
        List<String> schedulesIDs = fetch.getPublicSchedules();
        int index = outputBoundary.chooseScheduleFromList(schedulesIDs.size());
        if (schedulesIDs.size() == 0)
            return;
        if (index != -1){
            AddPublicScheduleUseCase addPublicScheduleUseCase = new AddPublicScheduleUseCase(databaseInterface, outputBoundary);
            addPublicScheduleUseCase.addPublicSchedule(username, schedulesIDs.get(index));
        }
    }

    /**
     * Sends a user a reminder for the day they specified.
     **/
    public void reminderFor(String username, DayOfWeek dayOfWeek) {
        ReminderPromptUseCase reminder = new ReminderPromptUseCase(databaseInterface, outputBoundary);
        FetchSchedulesUseCase fetcher = new FetchSchedulesUseCase(databaseInterface, outputBoundary);
        reminder.remind(dayOfWeek, fetcher.getActiveSchedule(username));
    }
}
