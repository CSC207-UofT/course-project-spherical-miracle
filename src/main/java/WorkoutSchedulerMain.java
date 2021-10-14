import java.util.Objects;
import java.util.Scanner;

public class WorkoutSchedulerMain {
    public static void main(String[] args) {
        Workout[] plan = new Workout[7];
        System.out.println(plan[0] == null);
        UserDatabase userDatabase = new UserDatabase();
        boolean quit = false;
        Scanner in = new Scanner(System.in);
        while (!quit) {
            //InOut.out.println();
            // would rather have this for ease of refactoring,
            // make this class later.
            System.out.println("Type 'l' to login and 's' to signup or q to quit");
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
            while (!quit) { // TODO add a quit optino to this loop
                System.out.println("Type 'w' to add a workout:");
                input = in.nextLine();
                switch (input) {
                    case "w":
                        System.out.println("Enter the name of the schedule:");
                        String scheduleName = in.nextLine();
                        Schedule schedule = new Schedule(scheduleName);
                        while (!Objects.equals(input, "f")) {
                            System.out.println("Enter the type of workout you would like to add or 'f' if you are finished.");
                            String workoutName = in.nextLine(); // will this cause errors by overriding switch argument inside switch case?
                            if (!workoutName.equals("f")) {
                                // ask for the day the user wants the workout on, and the calories burnt for the workout.
                                // then add it to the day of the workout.
                                System.out.println("Enter the day of the workout as an integer (0-6, where 0 is Sunday):");
                                int day = in.nextInt();
                                // in.nextLine(); // get rid of endline char from last input
                                System.out.println("Enter the estimated calories burnt for the workout:");
                                int calories = in.nextInt();
                                schedule.addWorkout(day, new Workout(workoutName, calories));
                            }
                        }
                }

            }

        }
    }
}
