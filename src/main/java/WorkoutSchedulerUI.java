import Schedule.*;

import java.util.HashMap;
import java.util.Objects;
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
        DataAccess access = new DataAccess(mongoClient);
        ScheduleDatabase scheduleDatabase = new ScheduleDatabase();
        Scanner in = new Scanner(System.in);
        InOut commandController;
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
                        SessionController sessionController = new SessionController(access);
                        HashMap<String, String> userInfo = userInput(in, true);
                        if (sessionController.login(userInfo.get("username"), userInfo.get("password")))
                            loggedIn = true;
                        else
                            System.out.println("Username and password does not match. Please try again.");
                        break;
                    }
                    case "s": // TODO do something similar as login where we validate then use if to change validInput
                        HashMap<String, String> info = userInput(in, false);
                        UserController userController = new UserController(access);
                        if (userController.addUser(info.get("username"), info.get("password"), info.get("name"), info.get("email")))
                            System.out.println("Successfully signed up!");
                        else
                            System.out.println("Unsuccessful signup. Username is already taken.");
                        break;
                    case "q":
                        running = false;
                        InOut.quit();
                        break;
                    default:
                        System.out.println("Invalid input; Please try again");
                        //break;
                }
            }
            while (loggedIn) {
                System.out.println("Type 'c' to make a schedule or 'l' to logout:");
                String option = in.nextLine();
                switch (option) {
                    case "c":
                        System.out.println("Enter the name of the schedule:");
                        String scheduleName = in.nextLine();
                        Schedule schedule = new Schedule(scheduleName);
                        while (!Objects.equals(option, "f")) {
                            System.out.println("Enter the type of workout you would like to add or 'f' if you are finished.");
                            option = in.nextLine(); // will this cause errors by overriding switch argument inside switch case?
                            if (option.equals("f")) {
                                // ask for the day the user wants the workout on, and the calories burnt for the workout.
                                // then add it to the day of the workout.
                                String firstReminder = InOut.finalizeSchedule(schedule, scheduleDatabase);
                                System.out.println(firstReminder);
                            } else {
                                int day;
                                int calories;
                                while(true){
                                    try {
                                        System.out.println("Enter the day of the workout as an integer (0-6, where 0 is Sunday):");
                                        day = Integer.parseInt(in.nextLine());
                                        if (day < 0 || day > 6) {
                                            System.out.println("Please enter an integer from 0 to 6");
                                        } else {
                                            break;
                                        }
                                    } catch(NumberFormatException e) {
                                        System.out.println("Input is not an integer");
                                    }
                                }
                                while (true) {
                                    try {
                                        System.out.println("Enter the estimated calories burnt for the workout:");
                                        calories = Integer.parseInt(in.nextLine());
                                        if (calories < 0) {
                                            System.out.println("Please enter an integer greater than or equal to 0");
                                        } else {
                                            break;
                                        }
                                    } catch(NumberFormatException e) {
                                        System.out.println("Input is not an integer");
                                    }
                                }
                                InOut.createWorkout(schedule, option, day, calories);
                                in.nextLine(); // get rid of endline char from last input
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
            System.out.println("You have quit. Goodbye!");
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
        userInput.put("username", in.nextLine());
        System.out.println("Enter your password:");
        userInput.put("password", in.nextLine());
        if (!is_login) {
            System.out.println("Enter a name:");
            userInput.put("name", in.nextLine());
            System.out.println("Enter an email:"); // TODO if needed, validate the email address
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

}
