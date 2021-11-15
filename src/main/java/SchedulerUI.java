import Schedule.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**
 * The user interface for scheduling workout session in a user's schedule.
 */

import User.UserController;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

//To Disable the commandline logs
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;


import io.github.cdimascio.dotenv.Dotenv;

public class SchedulerUI {
    public static void main(String[] args) {
        MongoClient mongoClient = InitializeDB();
        DataAccess access = new DataAccess(mongoClient);
        ScheduleDatabase scheduleDatabase = new ScheduleDatabase();
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running) {
            //InOut.out.println();
            // would rather have this for ease of refactoring,
            // make this class later.
            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.println("Type 'l' to login and 's' to signup or 'q' to quit");
                switch (in.nextLine()) {
                    case "l": {
                        // login situation where it is checked if the inputted credentials are valid
                        SessionController sessionController = new SessionController(access);
                        // initializes the userInput Hashmap and collects all inputted details
                        HashMap<String, String> userInfo = userInput(in, true);
                        if (sessionController.login(userInfo.get("username"), userInfo.get("password")))
                            loggedIn = true;
                        else
                            System.out.println("Username and password does not match. Please try again.");
                        break;
                    }
                    case "s":
                        // signup situation where a user inputs info to make new account
                        // TODO do something similar as login where we validate then use if to change valid_input
                        HashMap<String, String> info = userInput(in, false);
                        UserController userController = new UserController(access);
                        if (userController.addUser(info.get("username"), info.get("password"), info.get("name"), info.get("email")))
                            System.out.println("Successfully signed up!");
                        else
                            System.out.println("Unsuccessful signup. Username is already taken.");
                        break;
                        // TODO: maybe put this into a helper in InOut
                    case "q":
                        running = false;
                        System.out.println("The program will now exit. See you soon!");
                        InOutController.quit();
                        break;
                    default:
                        System.out.println("Invalid input. Please try again");
                        //break;
                }
            }
            while (loggedIn) {
                System.out.println("Type 'c' to make a schedule or 'l' to logout:");
                String option = in.nextLine();
                switch (option) {
                    case "c":
                        // case of creating a schedule
                        System.out.println("Enter the name of the schedule");
                        String scheduleName = in.nextLine();
                        Schedule schedule = new Schedule(scheduleName);
                        int date = 1;
                        System.out.println("The days in a schedule are numbered 1 to 7, with 1 set as Sunday. Each day can have up to five different workouts.");
                        while (date != -1) {
                            try {
                                System.out.println("Enter a day to plan workout(s)/meal(s) for as an integer(1-7) or '-1' if you have finished making this schedule");
                                date = Integer.parseInt(in.nextLine());
                                if (date == -1) {
                                    String firstReminder = InOutController.finalizeSchedule(schedule, scheduleDatabase);
                                    System.out.println(firstReminder);
                                } else if ((date > 7) || (date < 1)) {
                                    System.out.println("Please enter an integer from 1 to 7");
                                } else { // populating the schedule with days
                                    Day day = new Day();
                                    while (!(option.equals("f"))) {
                                        System.out.println("Enter a 'w' to add a workout, 'm' to add a meal" +
                                                " or 'f' if you are finished for this day");
                                        option = in.nextLine();
                                        switch (option) {
                                            case "w": // add workouts into a day
                                                // TODO: put this code chunk into helper?
                                                int i = 0;
                                                while (i < 5) { // since each Schedule.Day object can contain up to 5 Workouts
                                                    System.out.println("Enter a workout name or 'f' if you are finished for this day");
                                                    option = in.nextLine();
                                                    if (option.equals("f")) {
                                                        break;
                                                    } else { // continue setting up workouts for a day
                                                        int calories;
                                                        System.out.println("Enter the calories burnt for this workout");
                                                        calories = Integer.parseInt(in.nextLine());
                                                        if (calories <= 0) {
                                                            System.out.println("Please enter a positive number");
                                                        } else {
                                                            Workout newWorkout = new Workout(option, calories);
                                                            InOutController.createWorkout(day, newWorkout);
                                                            i++;
                                                        }
                                                    }
                                                    schedule.setDay(date, day); // TODO: put in InOut.java and then send to use case
                                                }
                                                break;
                                            case "m": // add meals into a day
                                                System.out.println("Enter the name of a meal or 'f' if you are finished for this day");
                                                String result = in.nextLine();
                                                if (result.equals("f")) {
                                                    break;
                                                } else {
                                                    while (!result.equals("f")) {
                                                        System.out.println("Enter the number of calories for it");
                                                        int cal = Integer.parseInt(in.nextLine());
                                                        Meal meal = new Meal(result, cal);
                                                        day.addMeal(meal);
                                                        // TODO: implement summary of calories consumed each day?
                                                        // TODO: MAKE A HELPER FOR VALIDATING CALORIE AMOUNT?
                                                        // and use helper for both workouts and meals
                                                        System.out.println("Enter the name of a meal or 'f' if you are finished for this day");
                                                        result = in.nextLine();
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Input is not an integer");
                            }
                        }
                        break;
                    case "l":
                        loggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid input; Please try again");
                }
            }
            System.out.println("You have been logged out. Goodbye!");
        }
    }

    /**
     * Returns a HashMap of user account details that were inputted.
     * @param in the Scanner reading the user input
     * @param is_login whether the user is logging in or not
     */
    private static HashMap<String, String> userInput(Scanner in, boolean is_login) {
        HashMap<String, String> userInput = new HashMap<>();
        System.out.println("Enter your username:");
        String input = in.nextLine();
        while (!(validateUsername(input)) && !(is_login)){
            System.out.println("Try again! Make sure username is between 8 to 20 characters long \n" +
                    "with only alphanumeric characters and special characters.");
            System.out.println("Enter your username:");
            input = in.nextLine();
        }
        userInput.put("username", input);
        System.out.println("Enter your password:");
        input = in.nextLine();
        while (!(validatePassword(input)) && !(is_login)){
            System.out.println("Try again! Make sure password is between 8 to 30 characters long \n" +
                    "with no white spaces.");
            System.out.println("Enter your password:");
            input = in.nextLine();
        }
        userInput.put("password", input);
        if (!is_login) {
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

    /**
     * Returns if the information is valid.
     * @param email the information submitted by the user
     * @return if the email is valid (any alphanumeric + special chars with an @ and then alphanumeric chars.
     */
    private static boolean validateEmail(String email){
        return Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.]+$", email);
    }

    /**
     * Returns if the information is valid.
     * @param username the information submitted by the user
     * @return if the username is valid (any alphanumeric + special chars)
     */
    private static boolean validateUsername(String username){
        return Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+$", username) && username.length() <= 20
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

    public static MongoClient InitializeDB(){
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

}
