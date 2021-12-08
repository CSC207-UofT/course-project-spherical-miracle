package Domain.User.UseCase;

import Domain.User.Boundary.*;
import Domain.User.Entities.User;

/**
 * Calculates and returns User's BMI
 */
public class BMIUseCase {

    private final FetchUserUseCase fetchUserUseCase;
    private final UserOutputBoundary outputBoundary;

    /**
     * Constructs a use case that calculates and returns User's BMI.
     * @param outputBoundary output boundary for use case and presenter
     * @param fetchUserUseCase use case for getting a user information
     */
    public BMIUseCase(FetchUserUseCase fetchUserUseCase, UserOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
        this.fetchUserUseCase = fetchUserUseCase;
    }
    /**
     * Displays a BMI message.
     */
    public void BMIMessage(String username) {
        try {
            User user = fetchUserUseCase.getUser(username);
            Object userBMI = user.getBMI();
            String weightCategory;
            if (userBMI instanceof Double) {
                if ((Double) userBMI == 0) {
                    weightCategory = "N/A";
                } else if ((Double) userBMI < 18.5) {
                    weightCategory = "Underweight";
                } else if ((Double) userBMI < 24.9) {
                    weightCategory = "Healthy Weight";
                } else if ((Double) userBMI < 29.9) {
                    weightCategory = "Overweight";
                } else {
                    weightCategory = "Obesity";
                }
                outputBoundary.printHeightWeight(user.getHeight(),user.getWeight());
                outputBoundary.bmiMessage((Double) userBMI, weightCategory);
            } else {
                outputBoundary.print((String) userBMI);
            }
        } catch (UserDoesNotExistException ignored) {
        }
    }
}
