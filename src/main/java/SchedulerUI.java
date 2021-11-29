import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
/**
 * The user interface for scheduling workout session in a user's schedule.
 */

//To Disable the commandline logs


public class SchedulerUI {
    private final class Commands {
        static final String QUIT = "0";
        static final String LOGOUT = "1";
        static final String CREATE_SCHEDULE = "2";
        static final String VIEW_YOUR_SCHEDULES = "3";
        static final String VIEW_PUBLIC_SCHEDULES = "4";
        static final String TODAYS_REMINDER = "5";
    }

    private final Map<String, String> commands = setCommands();
    private final MainController mainController;
    private final Scanner in;

    private static Map<String, String> setCommands() {
        Map<String, String> commands = new HashMap<>();
        commands.put(Commands.QUIT, "Quit");
        commands.put(Commands.LOGOUT, "Logout");
        commands.put(Commands.CREATE_SCHEDULE, "Create a schedule");
        commands.put(Commands.VIEW_YOUR_SCHEDULES, "View your schedules");
        commands.put(Commands.VIEW_PUBLIC_SCHEDULES, "View available schedule templates");
        commands.put(Commands.TODAYS_REMINDER, "See your reminders for today");
        return commands;
    }

    public SchedulerUI(Scanner in, MainController mainController) {
        this.mainController = mainController;
        this.in = in;
    }

    /**
     * The home page of the App.
     */
    public void toHome() {
        System.out.println("Welcome! Here are your options.");
        Map<String, String> userInfo;
        while (true) {
            System.out.println("Type 'l' to login and 's' to signup or 'q' to quit:");
            switch (in.nextLine()) {
                case "l":
                    userInfo = getUserInfo(true);
                    if (mainController.login(userInfo.get("username"), userInfo.get("password")))
                        toMainMenu();
                    break;
                case "s":
                    userInfo = getUserInfo(false);
                    if (mainController.signup(userInfo.get("username"), userInfo.get("password"),
                            userInfo.get("name"), userInfo.get("email")))
                        toMainMenu();
                    break;
                case "q":
                    System.out.println("The program will now exit. See you soon!");
                    return;
                default:
                    System.out.println("Invalid input. Please try again");
            }
        }
    }

    private void toMainMenu() {
        String option = selectOption(commands);
        while (true) {
            switch (option) {
                case Commands.QUIT:
                    // TODO: Bad design, should let main function quit
                    System.exit(0);
                case Commands.LOGOUT:
                    mainController.logout();
                    return;
                case Commands.CREATE_SCHEDULE:
                    toCreateScheduleMenu();
                    break;
                case Commands.VIEW_YOUR_SCHEDULES:
                    mainController.viewMySchedules();
                    break;
                case Commands.VIEW_PUBLIC_SCHEDULES:
                    // TODO: NO option to make a schedule public yet
                    mainController.viewPublicSchedules();
                    break;
                case Commands.TODAYS_REMINDER:
                    // TODO: NOT DONE
                    DayOfWeek today = LocalDate.now().getDayOfWeek();
                    mainController.sendReminderForDay(today);

                    // TODO: Edit schedule menu
            }
            option = selectOption(commands);
        }
    }

    private void toCreateScheduleMenu() {
        System.out.println("Enter the name of the schedule");
        String scheduleName = in.nextLine();
        mainController.createSchedule(scheduleName);
    }

    private String selectOption(Map<String, String> commands) {
        System.out.println("Select an option from the list:");
        for (Map.Entry<String, String> command : commands.entrySet()) {
            System.out.println(command.getKey() + ": " + command.getValue());
        }
        String selection = in.nextLine();
        while (!commands.containsKey(selection)) {
            System.out.println("Please select an option from the list:");
            selection = in.nextLine();
        }
        return selection;
    }

    /**
     * Returns a Map of user account details that were inputted.
     *
     * @param isLogin whether the user is logging in or not
     */
    private Map<String, String> getUserInfo(boolean isLogin) {
        Map<String, String> userInput = new HashMap<>();
        System.out.println("Enter your username:");
        String input = in.nextLine();
        while (!(validateUsername(input)) && !(isLogin)) {
            System.out.println("Try again! Make sure username is between 8 to 20 characters long \n" +
                    "with only alphanumeric characters and special characters.");
            System.out.println("Enter your username:");
            input = in.nextLine();
        }
        userInput.put("username", input);
        System.out.println("Enter your password:");
        userInput.put("password", in.nextLine());
        while (!(validatePassword(input)) && !(isLogin)) {
            System.out.println("Try again! Make sure password is between 8 to 30 characters long \n" +
                    "with no white spaces.");
            System.out.println("Enter your password:");
            input = in.nextLine();
        }
        userInput.put("password", input);
        if (!isLogin) {
            System.out.println("Enter a name:");
            input = in.nextLine();
            while (!(validateName(input))) {
                System.out.println("Try again! Make sure the name is less than 40 characters long \n" +
                        "with only alphabetical characters.");
                System.out.println("Enter a name:");
                input = in.nextLine();
            }
            userInput.put("name", input);
            System.out.println("Enter an email:");
            input = in.nextLine();
            while (!(validateEmail(input))) {
                System.out.println("Try again! Make sure the email is formatted correctly.");
                System.out.println("Enter an email:");
                input = in.nextLine();
            }
            userInput.put("email", input);
        }
        return userInput;
    }

    /**
     * Returns if the information is valid.
     *
     * @param email the information submitted by the user
     * @return if the email is valid (any alphanumeric + special chars with an @ and then alphanumeric chars.
     */
    private boolean validateEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.]+$", email);
    }

    /**
     * Returns if the information is valid.
     *
     * @param username the information submitted by the user
     * @return if the username is valid (any alphanumeric + special chars)
     */
    private boolean validateUsername(String username) {
        return Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", username) && username.length() <= 20
                && username.length() >= 8;
    }

    /**
     * Returns if the information is valid.
     *
     * @param password the information submitted by the user
     * @return if the password is valid (no whitespace)
     */
    private boolean validatePassword(String password) {
        return Pattern.matches("^\\S+$", password)
                && password.length() <= 30 && password.length() >= 8;
    }

    /**
     * Returns if the information is valid.
     *
     * @param name the information submitted by the user
     * @return if the name is valid (only alphabetical characters)
     */
    private boolean validateName(String name) {
        return Pattern.matches("[a-zA-Z]+", name) && name.length() <= 40;
    }
}

