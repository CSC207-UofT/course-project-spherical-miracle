package Database;

import Domain.User.UseCase.UserDoesNotExistException;

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
    UserInfo loadUserWithUsername(String username) throws UserDoesNotExistException;

    /**
     * Save the user information to the database.
     * @param username - username of the user
     * @param password - password of the user
     * @param name - name of the user
     * @param email - email of the user
     */
    void saveUser(String username, String password, String name, String email);

    /**
     * Add a new height and weight information into users database.
     * @param username - username of the user
     * @param height - new entry of height the user
     * @param weight - new entry of weight the user
     */
    void addHeightWeight(String username, double height, double weight);

    /**
     * return the list of heights and weights for the username given.
     * @param username - username of the user
     * @return the BodyMeasurementRecord for the given user.
     */
    BodyMeasurementRecord getHeightsWeightsFor(String username);

    /**
     * Class containing lists of weights and heights of a user.
     */
    class BodyMeasurementRecord{
        private final List<Double> weights;
        private final List<Double> heights;
        private final List<LocalDate> dates;

        /**
         * Constructs a BodyMeasurementRecord object with the given information.
         * @param weights - the list of weights over time
         * @param heights - the list of heights over time
         * @param dates - the list of dates corresponding to when the weights and heights are added.
         */
        public BodyMeasurementRecord(List<Double> weights, List<Double> heights, List<LocalDate> dates){
            this.weights = weights;
            this.heights = heights;
            this.dates = dates;
        }

        /**
         * returns a list of weights
         * @return a list of double of weights
         */
        public List<Double> getWeights(){
            return weights;
        }

        /**
         * returns a list of heights
         * @return a list of double of heights
         */
        public  List<Double> getHeights(){
            return heights;
        }

        /**
         * returns a list of dates
         * @return a list of LocalDate, corresponding to each height/weight entry
         */
        public  List<LocalDate> getDates(){
            return dates;
        }

    }

    class UserInfo {
        private final String username;
        private final String name;
        private final String email;
        private final String password;
        private final Double height;
        private final Double weight;

        /**
         * Constructs a UserInfo object when there is no height and weight record.
         * @param username - the username of the user
         * @param password - the hashed password of the user
         * @param name - the name of the user
         * @param email - the email of the user
         */
        public UserInfo(String username, String password, String name, String email) {
            this(username, password, name, email, -1.0, -1.0);
        }

        /**
         * Constructs a UserInfo object when there is at least one height and weight record.
         * @param username - the username of the user
         * @param password - the hashed password of the user
         * @param name - the name of the user
         * @param email - the email of the user
         * @param height - the newest height information of the user
         * @param weight - the newest weight information of the user
         */
        public UserInfo(String username, String password, String name, String email, Double height, Double weight) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.email = email;
            this.height = height;
            this.weight = weight;
        }

        /**
         * return the username
         * @return username of the user
         */
        public String getUsername() {
            return username;
        }

        /**
         * return the name of the user
         * @return name of the user
         */
        public String getName() {
            return name;
        }

        /**
         * return email of the user
         * @return email of the user
         */
        public String getEmail() {
            return email;
        }

        /**
         * return password of the user
         * @return password of the user
         */
        public String getPassword() {
            return password;
        }

        /**
         * return the latest height information of the user
         * @return height of the user
         */
        public Double getHeight() {
            return height;
        }

        /**
         * return the latest weight information of the user
         * @return weight of the user
         */
        public Double getWeight() {
            return weight;
        }

        /**
         * Check if the user has BodyMeasurements recorded or not
         * @return true if they have height & weight recorded, otherwise return false.
         */
        public boolean hasBodyMeasurements() {
            return height != -1.0;
        }
    }
}
