package Domain.User.Boundary;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Output boundary for the UserController calling use cases.
 */
public interface UserOutputBoundary {

    /**
     * Prints out a message depending on if the user was successfully logged in.
     */
    void loginMessage(boolean loggedIn);

    /**
     * Prints out a message depending on if the user was successfully logged out.
     */
    void logoutMessage(String name);

    /**
     * Prints out a message depending on if a user was successfully signed up.
     */
    void signupMessage(boolean signedUp);

    /**
     * Prints out a message about a user's height and weight.
     */
    void printHeightWeight(Double height, Double weight);

    /**
     * Prints out a message about a user's BMI.
     */
    void bmiMessage(double bmi, String weightCategory);

    /**
     * Prompts user about their preferred measurement units.
     */
    String[] askUnitType();

    /**
     * Prompts user about updating their height or weight.
     */
    Double askMeasurements(String message);

    /**
     * Prints out a specific message.
     */
    void print(String message);

    /**
     * Prompts user about a starting date to view their measurement history from.
     */
    LocalDate askDate();

    /**
     * Prints out a list representing the height and weight history of a user.
     */
    boolean printListOfHeightWeight(List<Map<String,Object>> days);

    /**
     * Prints out a string message when there is no previous entry found for heights and weight record.
     */
    void noEntryFoundMessage(Object lastDate);
}
