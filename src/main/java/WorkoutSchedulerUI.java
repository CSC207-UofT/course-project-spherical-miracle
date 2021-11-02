import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
/**
 * The user interface for scheduling workout session in a user's schedule.
 */

public class WorkoutSchedulerUI {
    public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase();
        ScheduleDatabase scheduleDatabase = new ScheduleDatabase();
        boolean quit = false;
        Scanner in = new Scanner(System.in);
        InOutController commandController;
        while (!quit) {
            //InOut.out.println();
            // would rather have this for ease of refactoring,
            // make this class later.
            String option;
            boolean valid_input = false;
            while (!valid_input) {
                System.out.println("Type 'l' to login and 's' to signup or 'q' to quit");
                option = in.nextLine();
                switch (option) {
                    case "l": {
                        SessionController sessionController = new SessionController(userDatabase);
                        HashMap<String, String> userInfo = userInput(in, true);
                        if (sessionController.login(userInfo.get("username"), userInfo.get("password"))) {
                            valid_input = true;
                        } else {
                            System.out.println("Incorrect credentials. Please try again.");
                        }
                        break;
                    }
                    case "s": // TODO do something similar as login where we validate then use if to change valid_input
                        HashMap<String, String> userInfo = userInput(in, false);
                        String result = InOutController.register(userInfo.get("username"), userInfo.get("password"),
                                userInfo.get("name"), userInfo.get("email"), userDatabase);
                        System.out.println(result);
                        valid_input = true;
                        break;
                    case "q":
                        quit = true;
                        valid_input = true;
                        InOutController.quit();
                        break;
                    default:
                        System.out.println("Invalid input; Please try again");
                        //break;
                }
            }
            while (!quit) {
                System.out.println("Type 'c' to make a schedule or 'q' to quit:");
                option = in.nextLine();
                switch (option) {
                    case "c":
                        System.out.println("Enter the name of the schedule:");
                        String scheduleName = in.nextLine();
                        Schedule schedule = new Schedule(scheduleName);
                        // everything above remains the same

                        int date = 0;
                        while (!Objects.equals(date, -1)) {
                            try {
                                System.out.println("Enter the date of the workout as an integer(0-6) or '-1' if you are completely finished:");
                                date = Integer.parseInt(in.nextLine());
                                if (date < -1 || date > 6) {
                                    System.out.println("Please enter an integer from 0 to 6");
                                }
                                else if (date == -1){
                                    String firstReminder = InOutController.finalizeSchedule(schedule, scheduleDatabase);
                                    System.out.println(firstReminder);
                                }
                                else {
                                    Day day = new Day();
                                    int i = 0;
                                    while(i < 5){
                                        System.out.println("Enter a workout name or 'f' if you are finished the day");
                                        option = in.nextLine();
                                        if (option.equals("f")) {
                                            break;
                                        } else {
                                            int calories;
                                            System.out.println("Enter the calories burnt for this workout");
                                            calories = Integer.parseInt(in.nextLine());
                                            if (calories <= 0) {
                                                System.out.println("Please enter a positive number");
                                            }
                                            else {
                                                Workout newWorkout = new Workout(option, calories);
                                                InOutController.createWorkout(day, newWorkout);
                                                i++;
                                            }
                                        }
                                        schedule.setDay(date, day); // TODO: put in InOut.java and then sent to use case
                                    }
                                }
                            } catch(NumberFormatException e) {
                                System.out.println("Input is not an integer");
                            }

                        }

                        break;
                    case "q":
                        quit = true;
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
}
