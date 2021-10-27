import java.util.Objects;
import java.util.Scanner;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import io.github.cdimascio.dotenv.Dotenv;
import org.bson.types.ObjectId;

public class WorkoutSchedulerMain {
    public static void main(String[] args) {
        MongoClient mongoClient = InitializeDB();
        DataAccess access = new DataAccess(mongoClient);
        UserDatabase userDatabase = new UserDatabase();
        ScheduleDatabase scheduleDatabase = new ScheduleDatabase();
        boolean quit = false;
        Scanner in = new Scanner(System.in);
        InOut commandController;
        while (!quit) {
            //InOut.out.println();
            // would rather have this for ease of refactoring,
            // make this class later.
            String input;
            boolean valid_input = false;
            while (!valid_input) {
                System.out.println("Type 'l' to login and 's' to signup or 'q' to quit");
                input = in.nextLine();
                switch (input) {
                    case "l":
                        // TODO make an existing login
                        valid_input = true;
                        break;
                    case "s":
                        System.out.println("Enter a username:");
                        String username = in.nextLine();
                        System.out.println("Enter an email:"); // TODO if needed, validate the email address
                        String email = in.nextLine();
                        System.out.println("Enter a password:");
                        String password = in.nextLine();
                        System.out.println("Enter a name:");
                        String name = in.nextLine();
                        String result = InOut.register(username, password, name, email, userDatabase);
                        System.out.println(result);
                        valid_input = true;
                        break;
                    case "q":
                        quit = true;
                        valid_input = true;
                        InOut.quit();
                        break;
                    default:
                        System.out.println("Invalid input; Please try again");
                        break;
                }
            }
            while (!quit) {
                System.out.println("Type 'c' to make a schedule or 'q' to quit:");
                input = in.nextLine();
                switch (input) {
                    case "c":
                        System.out.println("Enter the name of the schedule:");
                        String scheduleName = in.nextLine();
                        Schedule schedule = new Schedule(scheduleName);
                        while (!Objects.equals(input, "f")) {
                            System.out.println("Enter the type of workout you would like to add or 'f' if you are finished.");
                            input = in.nextLine(); // will this cause errors by overriding switch argument inside switch case?
                            if (input.equals("f")) {
                                // ask for the day the user wants the workout on, and the calories burnt for the workout.
                                // then add it to the day of the workout.
                                String firstReminder = InOut.finalizeSchedule(schedule, scheduleDatabase);
                                System.out.println(firstReminder);
                            } else {
                                System.out.println("Enter the day of the workout as an integer (0-6, where 0 is Sunday):");
                                int day = in.nextInt();
                                System.out.println("Enter the estimated calories burnt for the workout:");
                                int calories = in.nextInt();
                                InOut.createWorkout(schedule, input, day, calories);
                                in.nextLine(); // get rid of endline char from last input
                            }
                        }
                        break;
                    case "q":
                        quit = true;
                }
            }
            System.out.println("You have quit. Goodbye!");
        }

    }
    public static MongoClient InitializeDB(){
        Dotenv dotenv = Dotenv.load();
        ConnectionString URI = new ConnectionString(dotenv.get("URI"));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(URI)
                .build();
        return MongoClients.create(settings);
    }

}
