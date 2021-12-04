package Schedule;

import java.time.DayOfWeek;
import java.util.List;

import Schedule.Boundary.*;
import Schedule.UseCase.*;

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
        ManageScheduleUseCase c = new ManageScheduleUseCase(databaseInterface, outputBoundary);
        System.out.println("For each of the 7 days in your schedule, you can have up to five different workouts.");
        return c.createSchedule(scheduleName, username);
    }


    /**
     * Removes the schedule from a user's database of schedules.
     * @param name the name of the schedule being removed
     */
    public void removeSchedule(String name) {
        //TODO: validating inputs
    }

    public List<String> viewListOfSchedule(String username){
        return fetch.getScheduleAssociatedWith(username);
    }

    public void displayDeleteActivateSchedule(String username, List<String> schedulesIDs) {
        int index = outputBoundary.chooseScheduleFromList(schedulesIDs.size());
        if (schedulesIDs.size() == 0)
            return;
        if (index != -1) {
            while (true) {
                String option = outputBoundary.detailDeleteActivateOption();
                if (option.equalsIgnoreCase("delete")) {
                    RemoveScheduleUseCase removeScheduleUseCase = new RemoveScheduleUseCase(databaseInterface, outputBoundary);
                    removeScheduleUseCase.removeSchedule(username, schedulesIDs.get(index));
                } else if (option.equalsIgnoreCase("detail")) {
                    DisplayScheduleUseCase display = new DisplayScheduleUseCase(outputBoundary);
                    display.displaySchedule(fetch.getScheduleWithID(schedulesIDs.get(index)));
                }
                else if (option.equalsIgnoreCase("edit")){
                    ManageScheduleUseCase manager = new ManageScheduleUseCase(databaseInterface, outputBoundary);
                    manager.editSchedule(schedulesIDs.get(index), username);
                }
                else if (option.equalsIgnoreCase("a")){
                    SetActiveScheduleUseCase setActiveScheduleUseCase = new SetActiveScheduleUseCase(databaseInterface, outputBoundary);
                    setActiveScheduleUseCase.setAsActiveSchedule(username, fetch.getScheduleWithID(schedulesIDs.get(index)));
                } else if (option.equalsIgnoreCase("r")){
                    break;
                }
            }
        }
    }

    /**
     * Displays the list of public schedules
     */
    public void viewPublicSchedules() {
        List<String> schedulesIDs = fetch.getPublicSchedules();

    }

    public void setActiveSchedule(List<String> schedulesIDs, String username){
        int index = outputBoundary.activeSchedulePrompt(schedulesIDs.size());
        if (schedulesIDs.size() == 0)
            return;
        if (index != -1) {
            SetActiveScheduleUseCase setActiveScheduleUseCase = new SetActiveScheduleUseCase(databaseInterface, outputBoundary);
            setActiveScheduleUseCase.setAsActiveSchedule(username, fetch.getScheduleWithID(schedulesIDs.get(index)));
        }
    }

    public String reminderFor(String username, DayOfWeek dayOfWeek) {
        ReminderPromptUseCase reminder = new ReminderPromptUseCase(databaseInterface, outputBoundary);
        return reminder.remind(username, dayOfWeek);
    }

//    private void selectAndDisplaySchedule(List<String> schedulesIDs) {
//        int index = outputBoundary.viewSpecificSchedule(schedulesIDs.size());
//        if (schedulesIDs.size() == 0)
//            return;
//        if (index != -1) {
//            DisplayScheduleUseCase display = new DisplayScheduleUseCase(outputBoundary);
//            display.displaySchedule(fetch.getScheduleWithID(schedulesIDs.get(index)));
//        }
//    }
}
