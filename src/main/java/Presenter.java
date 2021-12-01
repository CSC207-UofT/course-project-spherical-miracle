import Schedule.Boundary.RemoveScheduleInputBoundary;
import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.ScheduleDataAccess;
import Schedule.UseCase.FetchSchedulesUseCase;
import Schedule.UseCase.RemoveScheduleUseCase;
import User.Boundary.*;

import java.time.DayOfWeek;
import java.util.*;

public class Presenter implements UserOutputBoundary, ScheduleOutputBoundary {

    private Scanner in = new Scanner(System.in);

    public class Messages {
        static final String WELCOME_MESSAGE = "Welcome! Here are your options:";
        static final String INVALID_INPUT = "Invalid input. Try again.";
        static final String CREATE_SCHEDULE_OPTIONS = "Type 'c' to make changes to a day or 's' to save and return to the main menu.";
    }

    public int getNumberBetweenInclusive(int min, int max) {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                int n = Integer.parseInt(in.nextLine());
                if (min <= n && n <= max)
                    return n;
            } catch (NumberFormatException e) {
            }
            System.out.println("Please enter a number between " + min + " and " + max);
        }
    }
    @Override
    public void loginMessage(boolean loggedIn) {
        if (loggedIn) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Username and password does not match. Please try again.");
        }
    }

    @Override
    public void logoutMessage(String name) {
        System.out.println("You have been logged out. Goodbye " + name + "!");
    }

    @Override
    public void signupMessage(boolean signedUp) {
        if (signedUp) {
            System.out.println("Sign up successful!");
        } else {
            System.out.println("Unsuccessful signup. Username is already taken.");
        }

    }

    public void scheduleList(String listSchedules) {
        // TODO: do this but make two

    }

    @Override
    public void scheduleMadeMessage(String returnMessage) {

    }

    @Override
    public void scheduleInfoMessage(String info) {
        System.out.println(info);
    }

    @Override
    public void something(boolean signedUp) {
        
    }

    @Override
    public void listSchedules(List<String> schedules) {
        System.out.println("These are your kept schedules. \n");
        for (String scheduleName: schedules) {
            System.out.println((schedules.indexOf(scheduleName)) + ". " + scheduleName);
        }
    }

    @Override
    public void currentActiveSchedule(String scheduleName){
        System.out.println("your current active schedule is " + scheduleName);
    }

    @Override
    public void noActiveSchedule(){
        System.out.println("You don't have an active schedule yet.");
    }

    @Override
    public void deleteSchedule(String username, String scheduleName) {
            // TODO: validate if inputted name is valid in user's schedule, make use case for it
            ScheduleDataAccess things = null;
            RemoveScheduleInputBoundary thing = new RemoveScheduleUseCase(null);
            thing.remove(username, scheduleName);
            System.out.println("Schedule " + scheduleName + " has been successfully deleted!");
            // if user didn't input name of a valid schedule that exists in their collection
            // System.out.println("Invalid input. Try again.");

    }

    @Override
    public String setActive() {
        return null;
    }

    @Override
    public boolean isPublic() {
        System.out.println("Enter 't' if you want the schedule to be public, if not enter 'f'.");
        String option;
        while (true) {
            option = in.nextLine();
            if (option.equals("t"))
                return true;
            else if (option.equals("f"))
                return false;
            System.out.println(Messages.INVALID_INPUT);
        }

    }

    @Override
    public void reminderPrompt(String s) {
        System.out.println(s);
    }

    @Override
    public int activeSchedulePrompt(int size) {
        return scheduleList(size, "Enter the number of the schedule that you want to activate. Or -1 to go back.");
    }

    @Override
    public int viewSpecificSchedule(int size) {
        return scheduleList(size, "Enter the number of the schedule that you would like to view. Or -1 to go back.");
    }

    private int scheduleList(int size, String message){
        Scanner in = new Scanner(System.in);
        if (size == 0) {
            System.out.println("There are no schedules available here. Go create some!");
            return -1;
        }
        System.out.println(message);
        while (true) {
            try {
                int index = Integer.parseInt(in.nextLine());
                if (-1 <= index && index <= size - 1)
                    return index;
            } catch (NumberFormatException e ) {}
            System.out.println(Messages.INVALID_INPUT);
        }
    }

    @Override
    public String selectEditOrSave() {
        System.out.println(Messages.CREATE_SCHEDULE_OPTIONS);
        String option;
        while (true) {
            option = in.nextLine();
            if (option.equals("c") || option.equals("s"))
                return option;
            System.out.println(Messages.INVALID_INPUT);
        }
    }

    @Override
    public DayOfWeek selectDay() {
        System.out.println("Please select a day (1-7):");
        for (DayOfWeek d : DayOfWeek.values()) {
            System.out.println(d.getValue() + ". " + d.name());
        }
        int dayOfWeek;
        while (true) {
            try {
                dayOfWeek = Integer.parseInt(in.nextLine());
                if (1 <= dayOfWeek && dayOfWeek <= 7)
                    return DayOfWeek.of(dayOfWeek);
            } catch (NumberFormatException e) {}
            System.out.println(Messages.INVALID_INPUT);
        }
    }

    @Override
    public Map<String, String> getNameAndCalories(String type) {
        Map<String, String> nameAndCalories = new HashMap<>();
        System.out.println("Enter the name of the " + type);
        String name = in.nextLine();
        nameAndCalories.put("name", name);
        System.out.println("Enter the calories for the " + type);
        int calories;
        while (true) {
            try {
                calories = Integer.parseInt(in.nextLine());
                if (calories >= 0) {
                    nameAndCalories.put("calories", String.valueOf(calories));
                    return nameAndCalories;
                }
            } catch (NumberFormatException e) {}
            System.out.println(Messages.INVALID_INPUT);
        }
    }

    @Override
    public void showAddWorkoutResult(int result, String name) {
        String output;
        if (result == 0) {
            output = "You have added the Workout " + name;
        }
        else if (result == 1) {
            output = "There are too many workouts in this day. Try again.";
        }
        else {
            assert result == 2;
            output = "There is already a workout in this day with the same name. Try again.";
        }
        System.out.println(output);
    }

    @Override
    public String createDayPrompt() {
        System.out.println("Type 'w' to create a workout, 'm' to create a meal, " +
                "or 'f' if you are finished for this day:");
        String option;
        while (true) {
            option = in.nextLine();
            if (option.equals("w") || option.equals("m") || option.equals("f"))
                return option;
            System.out.println(Messages.INVALID_INPUT);
        }

    }

}
