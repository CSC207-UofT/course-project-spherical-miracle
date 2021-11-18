import Schedule.*;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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

public class WorkoutSchedulerUI {
    public static void main(String[] args) {
        MongoClient mongoClient = InitializeDB();
        DataAccess databaseAccess = new DataAccess(mongoClient);
        ScheduleDatabase scheduleDatabase = new ScheduleDatabase();
        SessionController session = new SessionController(databaseAccess);
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running) {
            while (!session.loggedIn()) {
                System.out.println("Type 'l' to login and 's' to signup or 'q' to quit");
                switch (in.nextLine()) {
                    case "l":
                        // TODO: are the following comments necessary? i.e. does it improve readability?
                        // login situation where it is checked if the inputted credentials are valid
                        HashMap<String, String> userInfo = userInput(in, true);
                        if (!session.login(userInfo.get("username"), userInfo.get("password")))
                            System.out.println("Username and password does not match. Please try again.");
                        break;
                    case "s":
                        // signup situation where a user inputs info to make new account
                        // TODO do something similar as login where we validate then use if to change valid_input
                        HashMap<String, String> info = userInput(in, false);
                        UserController userController = new UserController(databaseAccess);
                        if (userController.addUser(info.get("username"), info.get("password"), info.get("name"), info.get("email")))
                            System.out.println("Successfully signed up!");
                        else
                            System.out.println("Unsuccessful signup. Username is already taken.");
                        break;
                        // TODO: maybe put this into a helper in InOut
                    case "q":
                        running = false;
                        System.out.println("The program will now exit. See you soon!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input. Please try again");
                }
            }
            while (session.loggedIn()) {
                System.out.println("Type 'c' to make a schedule or 'l' to logout:");
                String option = in.nextLine();
                switch (option) {
                    case "c":
                        ScheduleController scheduleController = new ScheduleController(databaseAccess);
                        // case of creating a schedule
                        System.out.println("The days in a schedule are numbered 1 to 7, with 1 set as Monday. Each day can have up to five different workouts.");
                        System.out.println("Enter the name of the schedule");
                        String scheduleName = in.nextLine();
                        // TODO: user input to get whether they want their schedule to be public

                        ArrayList<ArrayList<ArrayList<ArrayList<Object>>>> days = addDays();
                        scheduleController.createSchedule(scheduleName, session.getUsernameOfLoggedInUser(), false, days);
//                        String firstReminder = InOutController.finalizeSchedule(schedule, scheduleDatabase);
//                        System.out.println(firstReminder);
                        break;
                    case "l":
                        session.logout();
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
     * @param isLogin whether the user is logging in or not
     */
    private static HashMap<String, String> userInput(Scanner in, boolean isLogin) {
        HashMap<String, String> userInput = new HashMap<>();
        System.out.println("Enter your username:");
        userInput.put("username", in.nextLine());
        System.out.println("Enter your password:");
        userInput.put("password", in.nextLine());
        if (!isLogin) {
            System.out.println("Enter your name:");
            userInput.put("name", in.nextLine());
            System.out.println("Enter your email:"); // TODO validate the email address
            userInput.put("email", in.nextLine());
        }
        return userInput;
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

    public static ArrayList<ArrayList<ArrayList<ArrayList<Object>>>> addDays() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a day to plan workout(s)/meal(s) for as an integer (1-7) " +
                    "or '-1' if you have finished making this schedule");
            try {
                int option = Integer.parseInt(in.nextLine());
                if (option == -1)
                    // TODO: temporary
                    return new ArrayList<>();
                DayOfWeek dayOfWeek = DayOfWeek.of(option);
                Day day = new Day();
                addWorkoutsAndMeals(day);
                // schedule.setDay();
            } catch (NumberFormatException e) {
                System.out.println("Input is not an integer");
            } catch (DateTimeException e) {
                System.out.println("Please enter an integer from 1 to 7");
            }
        }
    }

    public static void addWorkoutsAndMeals(Day day) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 'w' to add a workout, " +
                    "'m' to add a meal " +
                    "or 'f' if you are finished for this day");
            switch (in.nextLine()) {
                case "f":
                    return;
                case "w":
                    // TODO: 5 should not be here (entity)
                    int i = 0;
                    while (i < 5) { // since each Schedule.Day object can contain up to 5 Workouts
                        System.out.println("Enter a workout name or 'f' if you are finished for this day");
                        String name = in.nextLine();
                        if (!name.equals("f")) {
                            configureWorkout(name, day);
                            i++;
                        }
                        break;
                    }
                    break;
                case "m": // add meals into a day
                    System.out.println("Enter the name of a meal or 'f' if you are finished for this day");
                    String result = in.nextLine();
                    if (!result.equals("f"))
                        configureMeal(result);
                    break;
                default:
                    System.out.println("Please enter 'w', 'm', or 'f'.");
            }
        }
    }

    public static void configureWorkout(String name, Day day) {
        Scanner in = new Scanner(System.in);
        int calories;
        System.out.println("Enter the calories burnt for this workout");
        calories = Integer.parseInt(in.nextLine());
        while (calories <= 0) {
            System.out.println("Please enter a positive number");
            calories = Integer.parseInt(in.nextLine());
        }
        Workout newWorkout = new Workout(name, calories);
        InOutController.createWorkout(day, newWorkout);
    }

    public static void configureMeal(String name) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of calories for it");
        int calories = Integer.parseInt(in.nextLine());
        Meal meal = new Meal(name, calories);
        // day.addMeal(meal);
        // TODO: implement summary of calories consumed each day?
        // TODO: MAKE A HELPER FOR VALIDATING CALORIE AMOUNT?
    }

}
