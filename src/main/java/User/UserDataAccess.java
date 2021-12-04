package User;

import User.UseCase.UserDoesNotExistException;

import java.time.LocalDate;
import java.util.List;

public interface UserDataAccess {

    /**
     * Returns an array of personal information of the specified user.
     * @param username - username of the target user
     * @return an array containing the personal information of the user.
     * In the order of username, password, name, email.
     * @throws UserDoesNotExistException when no existing users have the specified username.
     */
    Object[] loadUserWithUsername(String username) throws UserDoesNotExistException;

    /**
     * Save the user information to the database.
     * @param username - username of the user
     * @param password - password of the user
     * @param name - name of the user
     * @param email - email of the user
     */
    void saveUser(String username, String password, String name, String email);
    void editUser(String key, String change, String username);
    void addHeightWeight(String username, double height, double weight);

    BodyMeasurementRecord getHWListWith(String username);

    class BodyMeasurementRecord{
        private String username;
        private List<Double> weight;
        private List<Double> height;
        private List<LocalDate> date;

        public BodyMeasurementRecord(String username, List<Double> weight, List<Double> height, List<LocalDate> date){
            this.username = username;
            this.weight = weight;
            this.height = height;
            this.date = date;
        }

        public List<Double> getWeight(){
            return weight;
        }

        public  List<Double> getHeight(){
            return height;
        }

        public  List<LocalDate> getDate(){
            return date;
        }

    }
}
