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
            // Note: InOut.out.println() does not work so don't try to use it
            String option;
            boolean valid_input = false;
            while (!valid_input) {
                System.out.println("Type 'l' to login and 's' to signup or 'q' to quit");
                option = in.nextLine();
                switch (option) {
                    case "l": {
                        // login situation where it is checked if the inputted credentials are valid
                        SessionController sessionController = new SessionController(userDatabase);
                        // initializes the userInput Hashmap and collects all inputted details
                        HashMap<String, String> userInfo = userInput(in, true);
                        if (sessionController.login(userInfo.get("username"), userInfo.get("password"))) {
                            valid_input = true; // checks if inputted info is in user database
                        } else {
                            System.out.println("Incorrect credentials. Please try again.");
                        }
                        break;
                    }
                    case "s":
                        // signup situation where a user inputs info to make new account
                        // TODO do something similar as login where we validate then use if to change valid_input
                        HashMap<String, String> userInfo = userInput(in, false);
                        // result describes whether user was successfully signed up or not
                        // TODO: maybe put this into a helper in InOut
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
                        System.out.println("Invalid input. Please try again");
                        //break;
                }
            }
            while (!quit) {
                System.out.println("Type 'c' to make a schedule or 'q' to quit:");
                option = in.nextLine();
                switch (option) {
                    case "c":
                        // case of creating a schedule
                        System.out.println("The days in a schedule are numbered 1 to 7, with 1 set as Sunday. Each day can have up to five different workouts.");
                        System.out.println("Enter the name of the schedule");
                        String scheduleName = in.nextLine();
                        Schedule schedule = new Schedule(scheduleName);
                        int date = 1;
                        while (!Objects.equals(date, -1)) {
                            try {
                                System.out.println("Enter a day to plan workout(s)/meal(s) for as an integer(1-7) or '-1' if you have finished making this schedule");
                                date = Integer.parseInt(in.nextLine());
                                if (date == -1){
                                    String firstReminder = InOutController.finalizeSchedule(schedule, scheduleDatabase);
                                    System.out.println(firstReminder);
                                }
                                else if ((date > 7) || (date < 1)){
                                    System.out.println("Please enter an integer from 1 to 7");
                                }
                                else { // populating the schedule with days
                                    Day day = new Day();
                                    while(!(option.equals("f"))){
                                                System.out.println("Enter a 'w' to add a workout, 'm' to add a meal" +
                                                        " or 'f' if you are finished for this day");
                                                option = in.nextLine();
                                                switch (option){
                                                    case "w": // add workouts into a day
                                                        // TODO: put this code chunk into helper?
                                                        int i = 0;
                                                        while(i < 5){ // since each Day object can contain up to 5 Workouts
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
                                                                }
                                                                else {
                                                                    Workout newWorkout = new Workout(option, calories);
                                                                    InOutController.createWorkout(day, newWorkout);
                                                                    i++;
                                                                }
                                                            }
                                                            schedule.setDay(date, day); // TODO: put in InOut.java and then send to use case
                                                        }
                                                    case "m": // add meals into a day
                                                        System.out.println("Enter the name of a meal or 'f' if you are finished for this day");
                                                        String result = in.nextLine();
                                                        if (result.equals("f")){
                                                            break;
                                                        } else {
                                                            System.out.println("Enter the number of calories for it");
                                                            int cal = Integer.parseInt(in.nextLine());
                                                            Meal meal = new Meal(result, cal);
                                                            day.addMeal(meal);
                                                            // TODO: implement summary of calories consumed each day?
                                                            // TODO: MAKE A HELPER FOR VALIDATING CALORIE AMOUNT?
                                                            // and use helper for both workouts and meals
                                                        }
                                        }
                                    }
                                }
                            } catch(NumberFormatException e) {
                                System.out.println("Input is not an integer");
                            }
                        }
                        break;
                    case "q":// other case where user chooses to quit
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid input; Please try again");
                }
            }
            System.out.println("You have quit the program and have been logged out. Goodbye!");
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
            System.out.println("Enter an email:"); // TODO validate the email address
            userInput.put("email", in.nextLine());
        }
        return userInput;
    }
}
