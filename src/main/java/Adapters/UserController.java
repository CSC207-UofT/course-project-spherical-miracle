package Adapters;

import Domain.User.Boundary.*;
import Domain.User.Entities.User;
import Domain.User.UseCase.*;
import Database.UserDataAccess;

/**
 * A controller that delegates actions for managing a database of users.
 */
public class UserController {

    private final UserDataAccess databaseInterface;
    private final UserOutputBoundary outputBoundary;


    /**

     * Constructs a controller that handles user-related actions.
     *  @param databaseInterface the access interface to the database
     * @param outputBoundary output boundary for User
     */
    public UserController(UserDataAccess databaseInterface, UserOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Returns true iff successfully creates a user with the given information. All fields must be non-empty.
     * @param username the user's username
     * @param password the user's password
     * @param name the user's name
     * @param email the user's email
     * @return whether the user's info was valid and the user was added to the database or not
     **/
    public boolean createUser(String username, String password, String name, String email) {
        FetchUserUseCase fetch = new FetchUserUseCase(databaseInterface);
        try {
            fetch.getUser(username);
            outputBoundary.signupMessage(false);
            return false;
        } catch (UserDoesNotExistException e) {
            CreateUserInputBoundary createUserInputBoundary = new CreateUserUseCase(databaseInterface, outputBoundary);
            createUserInputBoundary.createUser(username, password, name, email);
            outputBoundary.signupMessage(true);
            return true;
        }
    }

    /**
     * Remove the user from the UserDatabase.
     * @param username the user's username
     */
    public void removeUser(String username) {
    }

    public void getCurrentWeightHeightBMI(String username){
        FetchUserUseCase fetch = new FetchUserUseCase(databaseInterface);
        BMIUseCase bmiUseCase = new BMIUseCase(fetch, outputBoundary);
        bmiUseCase.BMIMessage(username);
    }

    public void addHeightWeight(String username){
        AddHeightWeightUseCase addHeightWeightUseCase = new AddHeightWeightUseCase(databaseInterface, outputBoundary);
        addHeightWeightUseCase.addHeightWeight(username);

    }

    private boolean userInfoIsValid(String username, String password, String name, String email) {
        String[] userInfo = {username, password, name, email};
        for (String info : userInfo) {
            // Could add more checks
            if (info.isBlank())
                return false;
        }
        return true;
    }

    public void viewListOfHeightWeightOvertime(String username){
        HeightWeightOvertimeUseCase heightWeightOvertimeUseCase = new HeightWeightOvertimeUseCase(outputBoundary,
                databaseInterface);
        heightWeightOvertimeUseCase.displayHeightWeightOvertime(username);
    }
}
