import java.util.Objects;
import java.util.Scanner;

public class WorkoutSchedulerMain {
    public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase();
        ScheduleDatabase scheduleDatabase = new ScheduleDatabase();
        boolean quit = false;
        Scanner in = new Scanner(System.in);
        while (!quit) {
            //InOut.out.println();
            // would rather have this for ease of refactoring,
            // make this class later.
            System.out.println("Type 'l' to login and 's' to signup or 'q' to quit");
            String input = in.nextLine();
            switch(input) {
                case "l":
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
                    ManageUser manageuser = new ManageUser(username, password, email, name, userDatabase);
                    if (manageuser.addUser()) {
                        System.out.println("Successfully signed up!");
                    } else {
                        System.out.println("Unsuccessful signup. Username is already taken.");
                    }
                    break;
                case "q":
                    quit = true;
                    break;
            }
            while (!quit) {
                System.out.println("Type 's' to make a schedule or 'q' to quit:");
                input = in.nextLine();
                switch (input) {
                    case "s":
                        System.out.println("Enter the name of the schedule:");
                        String scheduleName = in.nextLine();
                        Schedule schedule = new Schedule(scheduleName);
                        while (!Objects.equals(input, "f")) {
                            System.out.println("Enter the type of workout you would like to add or 'f' if you are finished.");
                            input = in.nextLine(); // will this cause errors by overriding switch argument inside switch case?
                            if (input.equals("f")) {
                                // ask for the day the user wants the workout on, and the calories burnt for the workout.
                                // then add it to the day of the workout.
                                scheduleDatabase.addSchedule(schedule);
                                System.out.println("This is your workout for day 0:");
                                Workout firstWorkout = schedule.getWorkout(0);
                                if (firstWorkout == null) {
                                    System.out.println("Rest Day");
                                } else {
                                    System.out.println(schedule.getWorkout(0).getName());
                                }

                            } else {
                                System.out.println("Enter the day of the workout as an integer (0-6, where 0 is Sunday):");
                                int day = in.nextInt();
                                System.out.println("Enter the estimated calories burnt for the workout:");
                                int calories = in.nextInt();
                                schedule.addWorkout(day, new Workout(input, calories));
                                in.nextLine(); // get rid of endline char from last input
                                }
                            }
                        break;
                    case "q":
                        quit = true;
                        }
                }

            }

        }
    }
