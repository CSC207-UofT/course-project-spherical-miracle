import Schedule.Boundary.ScheduleOutputBoundary;
import User.Boundary.*;

import java.time.DayOfWeek;
import java.util.*;

public class Presenter implements UserOutputBoundary, ScheduleOutputBoundary {

    private Scanner in = new Scanner(System.in);

    public class Messages {
        static final String WELCOME_MESSGAGE = "Welcome! Here are your options:";
        static final String INVALID_INPUT = "Invalid input. Try again.";
        static final String CREATE_SCHEDULE_OPTIONS = "Type 'e' to start creating or 's' to save and return to the main menu.";
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

    @Override
    public void scheduleList(String listSchedules) {

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
        for (String scheduleName: schedules) {
            System.out.println((schedules.indexOf(scheduleName)) + ". " + scheduleName);
        }
    }

    @Override
    public void deleteSchedule(String scheduleName) {

    }

    @Override
    public int viewSpecificSchedule(int size) {
        Scanner in = new Scanner(System.in);
        if (size == 0) {
            System.out.println("You have not created any schedules yet. Go create some!");
            return -1;
        }
        System.out.println("Enter the number of the schedule that you would like to view. Or -1 to go back. ");
        while (true) {
            try {
                int index = Integer.parseInt(in.nextLine());
                if (-1 <= index && index <= size - 1)
                    return index;
            } catch (NumberFormatException e ) {}
            System.out.println("Invalid input. Try again.");
        }
    }

    @Override
    public void outputTooManyWorkout() {
        System.out.println("You can't add any more workouts to this day.");
    }

    @Override
    public String createSchedulePrompt() {
        System.out.println(Messages.CREATE_SCHEDULE_OPTIONS);
        String option;
        while (true) {
            option = in.nextLine();
            if (option.equals("e") || option.equals("s"))
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
