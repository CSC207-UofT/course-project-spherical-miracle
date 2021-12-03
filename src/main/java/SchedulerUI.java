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
        static final String WEIGHT_HEIGHT_BMI = "6";
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
        commands.put(Commands.WEIGHT_HEIGHT_BMI, "To add information about your Height/Weight and see your BMI");
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
                    mainController.viewPublicSchedules();
                    break;
                case Commands.TODAYS_REMINDER:
                    DayOfWeek today = LocalDate.now().getDayOfWeek();
                    mainController.sendReminderForDay(today);
                    break;
                    // TODO: Edit schedule menu
                case Commands.WEIGHT_HEIGHT_BMI:
                    weightHeightBMIMenu();
                    break;
            }
        }
    }

    private void toCreateScheduleMenu() {
        System.out.println("NOT FINISHED YET!!");
        System.out.println("Enter the name of the schedule");
        String scheduleName = in.nextLine();
        mainController.createSchedule(scheduleName);
        System.out.println("For each of the 7 days in your schedule, you can have up to five different workouts.");
        Map<DayOfWeek, Map<String, List<Map<String, String>>>> scheduleDetails = new HashMap<>();
        while (true) {
            System.out.println("Type 'e' to start creating or 's' to save and return to the main menu.");
            switch (in.nextLine()) {
                case "s":
                    break;
                case "e":
                    int dayOfWeek;
                    do {
                        System.out.println("Please select a day (1-7):");
                        for (DayOfWeek c : DayOfWeek.values()) {
                            System.out.println(c.getValue() + ". " + c.name());
                        }
                        while (!in.hasNextInt()) {
                            System.out.println("Incorrect input. Enter a number between 1 and 7.");
                            in.next();
                        }
                        dayOfWeek = Integer.parseInt(in.nextLine());
                    } while (!(1 <= dayOfWeek && dayOfWeek <= 7));
                    scheduleDetails.put(DayOfWeek.of(dayOfWeek), createDayMenu(dayOfWeek));
                    break;
                default:
                    System.out.println("Incorrect input. Try again.");
            }
            // TODO: see createSchedule
//            mainController.createSchedule(scheduleName, scheduleDetails);
        }
    }

    private Map<String, List<Map<String, String>>> createDayMenu(int dayOfWeek) {
        Map<String, List<Map<String, String>>> workoutsAndMeals = new HashMap<>();
        workoutsAndMeals.put("workouts", new ArrayList<>());
        workoutsAndMeals.put("meals", new ArrayList<>());
        while (true) {
            System.out.println("Type 'w' to create a workout, 'm' to create a meal, " +
                    "or 'f' if you are finished for this day:");
           switch (in.nextLine()) {
                case "f":
                    return workoutsAndMeals;
                case "w":
                    workoutsAndMeals.get("workouts").add(toCreateWorkoutMenu(dayOfWeek));
                    break;
                case "m":
                    workoutsAndMeals.get("meals").add(toCreateMealMenu());
                    break;
                default:
                    System.out.println("Incorrect input. Try again.");
            }
        }
    }

    private Map<String, String> toCreateWorkoutMenu(int dayOfWeek) {
        System.out.println("Enter a workout name:");
        String workoutName = in.nextLine();
        while (mainController.checkDuplicateWorkout(workoutName, DayOfWeek.of(dayOfWeek), "1")) {
            System.out.println("There is already a workout in this day with that name.");
            System.out.println("Enter another workout name:");
            workoutName = in.nextLine();
        }
        int calories = 0;
        while (calories <= 0) {
            System.out.println("Enter the calories burnt for this workout:");
            if (!in.hasNextInt()) {
                System.out.println("Please enter a positive number. Try again:");
                in.nextLine();
                continue;
            }
            calories = Integer.parseInt(in.nextLine());
            if (calories <= 0) {
                System.out.println("Please enter a positive number. Try again.");
            }
        }
        Map<String, String> workout = new HashMap<>();
        workout.put("workoutName", workoutName);
        workout.put("calories", String.valueOf(calories));
        return workout;
    }

    private Map<String, String> toCreateMealMenu() {
        // TODO finish this method - the following is the old code

//        System.out.println("Enter the name of a meal or 'f' if you are finished adding meal plans for this day");
//        String result = in.nextLine();
//        if (result.equals("f")) {
//            break;
//        } else {
//            while (!result.equals("f")) {
//                System.out.println("Enter the number of calories for it");
//                int cal = Integer.parseInt(in.nextLine());
//                Meal newMeal = new Meal(result, cal);
//                InOutController.createMeal(day, newMeal);
//                day.addMeal(newMeal);
//                // TODO: implement summary of calories consumed each day?
//                // and use helper for both workouts and meals
//                System.out.println("Enter the name of a meal or 'f' if you are finished for this day");
//                result = in.nextLine();
//            }
//        }
        return new HashMap<>();
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

    private void weightHeightBMIMenu() {
        while (true) {
            System.out.println("Type 'c' to view your current Weight/Height, 'a' to add a record of your Weight/Height or 'r' to return to the main menu.");
            switch (in.nextLine()) {
                case "r":
                    return;
                case "c":
                    mainController.currentWeightHeightBMI();
                    break;
                case "a":
                    String[] unitType = askUnitType();
                    Double height = askMeasurements("height");
                    Double weight = askMeasurements("weight");
                    mainController.addHeightWeight(unitType[0], unitType[1], height, weight);
                    break;
                default:
                    System.out.println("Incorrect input. Try again.");
            }


        }
    }


    //todo: we probably can design this better
    private String[] askUnitType(){
        while (true) {
            System.out.println("Select your preferred unit for height. If centimeters, enter 'cm', if feet, enter 'f'.");
            String[] units = new String[2];
            units[0] = in.nextLine();
            if (units[0].equals("cm") || units[0].equals("f")) {
                System.out.println("Select your preferred unit for weight. If kilograms, enter 'kg', if lbs, enter 'lbs'.");
                units[1] = in.nextLine();
                while(!units[1].equals("kg") && !units[1].equals("lbs")) {
                    System.out.println("Incorrect input. Try again.");
                    units[1] = in.nextLine();
                }
                    return units;
            } else{
                System.out.println("Incorrect input. Try again.");
            }
        }
    }

    private Double askMeasurements(String message) {
        while (true) {
            System.out.println("Input your " + message + ", if it didn't change from last time, put -1.");
            try {
                double measurement = in.nextDouble();
                if (!(measurement == -1) && measurement <= 0) {
                    System.out.println("Value must be positive value. Please try again.");
                }
                return measurement;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input. Try again.");
            }

        }
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

