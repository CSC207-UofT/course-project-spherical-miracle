import User.UseCase.CreateUserUseCase;
import Schedule.Boundary.ScheduleOutputBoundary;

import java.util.List;

public class Presenter implements CreateUserUseCase.UserOutputBoundary, ScheduleOutputBoundary {
    @Override
    public void loginPrompt(String prompt) {
    }

    @Override
    public void loginMessage(boolean loggedIn) {
        if (loggedIn) {
            System.out.println("Login successful!");

        } else {
            System.out.println("Username and password does not match. Please try again.");

        }
    }

    @Override
    public void logoutMessage(String name) {
        System.out.println("You have been logged out. Goodbye " + name + "!");
    }

    @Override
    public void signupMessage(boolean signedUp) {
        if (signedUp) {
            System.out.println("Sign up successful!");
        } else {
            System.out.println("Unsuccessful signup. Username is already taken.");
        }

    }

    @Override
    public void scheduleList(String listSchedules) {

    }

    @Override
    public void scheduleMadeMessage(String returnMessage) {

    }

    @Override
    public void scheduleInfoMessage(String name) {

    }

    @Override
    public void something(boolean signedUp) {
        
    }

    @Override
    public void listSchedules(List<String> schedules) {
        for (String scheduleName: schedules) {
            System.out.println((schedules.indexOf(scheduleName) + 1) + ". " + scheduleName + "\n");
        }
    }

    @Override
    public void deleteSchedule(String scheduleName) {

    }

}