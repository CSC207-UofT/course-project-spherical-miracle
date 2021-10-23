import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class WorkoutSchedulerMain {
    public static void main(String[] args) {
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
                    case "l": {
                        SessionController sessionController = new SessionController(userDatabase);
                        String[] userVal = UserInput(in, true);
                        if (sessionController.login(userVal[0], userVal[1])) {
                            valid_input = true;
                        } else {
                            System.out.println("Incorrect credentials. Please try again.");
                        }
                        break;
                    }
                    case "s": // TODO do something similar as login where we validate then use if to change valid_input
                        String[] userVal = UserInput(in, false);
                        String result = InOut.register(userVal[0], userVal[1], userVal[2], userVal[3], userDatabase);
                        System.out.println(result);
                        // valid_input = true;
                        break;
                    case "q":
                        quit = true;
                        valid_input = true;
                        InOut.quit();
                        break;
                    default:
                        System.out.println("Invalid input; Please try again");
                        //break;
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
                    default:
                        System.out.println("Invalid input; Please try again");
                }
            }
            System.out.println("You have quit. Goodbye!");
        }

    }

    private static String[] UserInput(Scanner in, boolean is_login) {
        System.out.println("Enter your username:");
        String username = in.nextLine();
        System.out.println("Enter your password:");
        String password = in.nextLine();
        if (is_login) {
            return new String[]{username, password};
        }
        else{
            System.out.println("Enter an email:"); // TODO if needed, validate the email address
            String email = in.nextLine();
            System.out.println("Enter a name:");
            String name = in.nextLine();
            return new String[]{username, password, name, email};
        }
    }

}
