import Schedule.*;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * The user interface for scheduling workout session in a user's schedule.
 */

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

//To Disable the commandline logs
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;


import io.github.cdimascio.dotenv.Dotenv;

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
        // TODO: Use constants as keys instead?
        commands.put(Commands.QUIT, "Quit");
        commands.put(Commands.LOGOUT, "Logout");
        commands.put(Commands.CREATE_SCHEDULE, "Create a schedule");
        commands.put(Commands.VIEW_YOUR_SCHEDULES, "View your schedules");
        commands.put(Commands.VIEW_PUBLIC_SCHEDULES, "View available schedule templates");
        return commands;
    }

    public SchedulerUI(Scanner in, MainController mainController) {
        this.mainController = mainController;
        this.in = in;
    }

    public void home() {
        DataAccess databaseGateway = new DataAccess(InitializeDatabase());
        ScheduleDatabase scheduleDatabase = new ScheduleDatabase();

        Presenter presenter = new Presenter();
        SessionController session = new SessionController(databaseGateway, presenter);
        System.out.println("Welcome! Here are your options:");
        while (true) {
            System.out.println("Type 'l' to login and 's' to signup or 'q' to quit");
            switch (in.nextLine()) {
                case "l":
                    HashMap<String, String> userInfo = userInput(in, true);
                    mainController.login(userInfo.get("username"), userInfo.get("password"));
                    break;
                case "s":
                    HashMap<String, String> info = userInput(in, false);
                    mainController.signup(info.get("username"), info.get("password"),
                            info.get("name"), info.get("email"));
                    break;
                case "q":
                    System.out.println("The program will now exit. See you soon!");
                    return;
                default:
                    System.out.println("Invalid input. Please try again");
            }
            mainMenu();
        }
//        System.out.println("Type 'c' to make a schedule or 'l' to logout:");
//        String option = in.nextLine();
//        switch (option) {
//            case "c":
//                // case of creating a schedule
//                System.out.println("Enter the name of the schedule");
//                String scheduleName = in.nextLine();
//                Schedule schedule = new Schedule(scheduleName);
//                int date = 1;
//                System.out.println("The days in a schedule are numbered 1 to 7, with 1 set as Sunday. Each day can have up to five different workouts.");
//                while (date != -1) {
//                    try {
//                        option = "";
//                        System.out.println("Enter a day to plan workout(s)/meal(s) for as an integer(1-7) or '-1' if you have finished making this schedule");
//                        date = Integer.parseInt(in.nextLine());
//                        if (date == -1) {
//                            String firstReminder = InOutController.finalizeSchedule(schedule, scheduleDatabase);
//                            // THIS IS WHERE REMINDER GETS PRINTED
//                            System.out.println(firstReminder);
//                        } else if ((date > 7) || (date < 1)) {
//                            System.out.println("Please enter an integer from 1 to 7");
//                        } else { // populating the schedule with days
//                            Day day = new Day();
//                            int i = 0;
//                            while (!(option.equals("f"))) {
//                                System.out.println("Enter a 'w' to add a workout, 'm' to add a meal" +
//                                        " or 'f' if you are finished for this day");
//                                option = in.nextLine();
//                                switch (option) {
//                                    case "w": // add workouts into a day
//                                        // TODO: put this code chunk into helper?
//                                        while (i < 5) { // since each Schedule.Day object can contain up to 5 Workouts
//                                            System.out.println("Enter a workout name or 'f' if you are finished adding workout plans for this day");
//                                            option = in.nextLine();
//                                            if (option.equals("f")) {
//                                                option = "";
//                                                break;
//                                            } else { // continue setting up workouts for a day
//                                                int calories = 0;
//                                                while(calories <= 0){
//                                                System.out.println("Enter the calories burnt for this workout");
//                                                calories = Integer.parseInt(in.nextLine()); //TODO: try catch int error
//                                                    if (calories <= 0) {
//                                                        System.out.println("Please enter a positive number");
//                                                        continue;
//                                                    }else{
//                                                        // TODO: Move all of these to a different file to follow clean arch rule.
//                                                        Workout newWorkout = new Workout(option, calories);
//                                                        InOutController.createWorkout(day, newWorkout);
//                                                        i++;
//                                                    }
//                                                }
//                                            }
//
//                                        }
//                                        continue;
//                                    case "m": // add meals into a day
//                                        System.out.println("Enter the name of a meal or 'f' if you are finished adding meal plans for this day");
//                                        String result = in.nextLine();
//                                        if (result.equals("f")) {
//                                            break;
//                                        } else {
//                                            while (!result.equals("f")) {
//                                                System.out.println("Enter the number of calories for it");
//                                                int cal = Integer.parseInt(in.nextLine());
//                                                // TODO: Move all of these to a different file to follow clean arch rule.
//                                                Meal newMeal = new Meal(result, cal);
//                                                InOutController.createMeal(day, newMeal);
//                                                day.addMeal(newMeal);
//                                                // TODO: implement summary of calories consumed each day?
//                                                // TODO: MAKE A HELPER FOR VALIDATING CALORIE AMOUNT?
//                                                // and use helper for both workouts and meals
//                                                System.out.println("Enter the name of a meal or 'f' if you are finished for this day");
//                                                result = in.nextLine();
//                                            }
//                                        }
//                                        break;
//                                }
//                            }
//                            // TODO: put in InOut.java and then send to use case
//                            if ((schedule.getDay(DayOfWeek.of(date)) == null)) {
//                                schedule.setDay(DayOfWeek.of(date), day); // TODO: put in InOut.java and then send to use case
//                                }
//                                else {
//                                    InOutController.mergeDay(day, schedule.getDay(DayOfWeek.of(date)));
//                                }
//                            }
//                        }catch (NumberFormatException e) {
//                        System.out.println("Input is not an integer");
//                    }
//                }
//                break;
//            case "l":
//                session.logout();
//                break;
//            default:
//                System.out.println("Invalid input; Please try again");
//        }
    }
//    System.out.println("You have been logged out. Goodbye!");

    /**
     * Returns a HashMap of user account details that were inputted.
     * @param in the Scanner reading the user input
     * @param isLogin whether the user is logging in or not
     */
    private static HashMap<String, String> userInput(Scanner in, boolean isLogin) {
        HashMap<String, String> userInput = new HashMap<>();
        System.out.println("Enter your username:");
        String input = in.nextLine();
        while (!(validateUsername(input)) && !(isLogin)){
            System.out.println("Try again! Make sure username is between 8 to 20 characters long \n" +
                    "with only alphanumeric characters and special characters.");
            System.out.println("Enter your username:");
            input = in.nextLine();
        }
        userInput.put("username", input);
        System.out.println("Enter your password:");
        userInput.put("password", in.nextLine());
        while (!(validatePassword(input)) && !(isLogin)){
            System.out.println("Try again! Make sure password is between 8 to 30 characters long \n" +
                    "with no white spaces.");
            System.out.println("Enter your password:");
            input = in.nextLine();
        }
        userInput.put("password", input);
        if (!isLogin) {
            System.out.println("Enter a name:");
            input = in.nextLine();
            while (!(validateName(input))){
                System.out.println("Try again! Make sure the name is less than 40 characters long \n" +
                        "with only alphabetical characters.");
                System.out.println("Enter a name:");
                input = in.nextLine();
            }
            userInput.put("name", input);
            System.out.println("Enter an email:");
            input = in.nextLine();
            while (!(validateEmail(input))){
                System.out.println("Try again! Make sure the email is formatted correctly.");
                System.out.println("Enter an email:");
                input = in.nextLine();
            }
            userInput.put("email", input);
        }
        return userInput;
    }

    private void mainMenu() {
        String option = selectOption(commands);
        switch (option) {
            case Commands.QUIT:
                // TODO: Bad design, should let main function quit
                System.exit(0);
            case Commands.LOGOUT:
                mainController.logout();
                break;
            case Commands.CREATE_SCHEDULE:
                createScheduleMenu();
                break;
            case Commands.VIEW_YOUR_SCHEDULES:
                mainController.viewMySchedules();
                break;
            case Commands.VIEW_PUBLIC_SCHEDULES:
                mainController.viewPublicSchedules();
                break;
        }
    }

    private void createScheduleMenu() {
        System.out.println("Enter the name of the schedule");
        String scheduleName = in.nextLine();
        System.out.println("For each of the 7 days in your schedule, you can have up to five different workouts.");
        while (true) {
            System.out.println("Type 'e' to start creating or 's' to save and return to the main menu.");
            switch (in.nextLine()) {
                case "s":
                    break;
                case "e":
                    int dayOfWeek;
                    do {
                        System.out.println("Please select a day (1-7):");
                        for (DayOfWeek c: DayOfWeek.values()) {
                            System.out.println(c.getValue() + ". " + c.name());
                        }
                        while (!in.hasNextInt()) {
                            System.out.println("Incorrect input. Enter a number between 1 and 7.");
                            in.next();
                        }
                        dayOfWeek = in.nextInt();
                    } while (dayOfWeek < 1 || dayOfWeek > 7);
                    break;
                default:
                    System.out.println("Incorrect input. Try again.");
            }
            mainController.createSchedule(scheduleName);
        }
    }

    private void createDayMenu() {
        System.out.println(" or 'f' if you are finished adding workout plans for this day");
        switch(in.nextLine()) {
            case "f":
                return;
        }
    }

    private void createWorkoutMenu() {
        System.out.println("Enter a workout name:");
        String workoutName = in.nextLine();
        int calories = 0;
        while (calories <= 0) {
            System.out.println("Enter the calories burnt for this workout");
            while (!in.hasNextInt()) {
                System.out.println("Please enter a positive number:");
                in.next();
            }
            calories = in.nextInt();
            if (calories <= 0) {
                System.out.println("Please enter a positive number");
            }
        }
        Map<String, String> workout = new HashMap<>();

    }

    /**
     * Returns if the information is valid.
     * @param email the information submitted by the user
     * @return if the email is valid (any alphanumeric + special chars with an @ and then alphanumeric chars.
     */
    private static boolean validateEmail(String email){
        return Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.]+$", email);
    }

    /**
     * Returns if the information is valid.
     * @param username the information submitted by the user
     * @return if the username is valid (any alphanumeric + special chars)
     */
    private static boolean validateUsername(String username){
        return Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", username) && username.length() <= 20
                && username.length() >= 8;
    }

    /**
     * Returns if the information is valid.
     * @param password the information submitted by the user
     * @return if the password is valid (no whitespace)
     */
    private static boolean validatePassword(String password){
        return Pattern.matches("^\\S+$", password)
                && password.length() <= 30 && password.length() >= 8;
    }

    /**
     * Returns if the information is valid.
     * @param name the information submitted by the user
     * @return if the name is valid (only alphabetical characters)
     */
    private static boolean validateName(String name){
        return Pattern.matches("[a-zA-Z]+", name) && name.length() <= 40;
    }

    public static MongoClient InitializeDatabase(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(ch.qos.logback.classic.Level.OFF);
        Dotenv dotenv = Dotenv.load();
        ConnectionString URI = new ConnectionString(dotenv.get("URI"));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(URI)
                .build();
        return MongoClients.create(settings);
    }

    public String selectOption(Map<String, String> commands) {
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
}
