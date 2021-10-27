import java.util.*;

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
                        String result = InOut.register(userInfo.get("username"), userInfo.get("password"),
                                userInfo.get("name"), userInfo.get("email"), userDatabase);
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
                                        day = Integer.parseInt(in.nextLine()); // .nextInt() does not work for some reason
                                        if (day < 0 || day > 6) {
                                            System.out.println("Please enter an integer from 0 to 6"); //print message
                                        } else {
                                            break;
                                        }
                                    } catch(NumberFormatException e) {
                                        System.out.println("Input is not an integer");
                                    }
                                }
                                while(true){
                                    try {
                                        System.out.println("Enter the estimated calories burnt for the workout:");
                                        calories = Integer.parseInt(in.nextLine()); // .nextInt() does not work for some reason
                                        if (calories < 0) {
                                            System.out.println("Please enter an integer greater than or equal to 0"); //print message
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
