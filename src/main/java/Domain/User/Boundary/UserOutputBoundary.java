package Domain.User.Boundary;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UserOutputBoundary {
    void loginMessage(boolean loggedIn);
    void logoutMessage(String name);
    void signupMessage(boolean signedUp);
    void printHeightWeight(Double height, Double weight);
    void bmiMessage(double bmi, String weightCategory);
    String[] askUnitType();
    Double askMeasurements(String message);
    void noBMI(String message);
    LocalDate askDate();
    boolean printListOfHeightWeight(List<Map<String,Object>> days);
    void noEntryFoundMessage(Object lastDate);
}
