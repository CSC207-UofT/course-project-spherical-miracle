package User.UseCase;

import User.Boundary.*;
import User.Entities.User;

/**
 * Calculates and returns User's BMI
 */
public class BMIUseCase {

    private final FetchUserUseCase fetchUserUseCase;
    private final UserOutputBoundary outputBoundary;

    /**
     * Constructs a use case that calculates and returns User's BMI.
     * @param outputBoundary
     * @param fetchUserUseCase
     */
    public BMIUseCase(UserOutputBoundary outputBoundary, FetchUserUseCase fetchUserUseCase) {
        this.outputBoundary = outputBoundary;
        this.fetchUserUseCase = fetchUserUseCase;
    }
    /**
     * Returns wether or not the BMI message was successfully returned.
     */
    public boolean BMIMessage(String username) {
        try {
            User user = fetchUserUseCase.getUser(username);
            Double userBMI = user.getBMI();
            String weightCategory;
            if (userBMI == 0) {
                weightCategory = "N/A";
            } else if (userBMI < 18.5) {
                weightCategory = "Underweight";
            } else if (userBMI < 24.9) {
                weightCategory = "Healthy Weight";
            } else if (userBMI < 29.9) {
                weightCategory = "Overweight";
            } else {
                weightCategory = "Obesity";
            }
            outputBoundary.bmiMessage(userBMI, weightCategory);
            return true;
        } catch (UserDoesNotExistException e) {
            return false;
        }
    }
}
