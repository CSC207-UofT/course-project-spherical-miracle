package Database;

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
    UserInfo loadUserWithUsername(String username) throws UserDoesNotExistException;

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

    BodyMeasurementRecord getHeightsWeightsFor(String username);

    class BodyMeasurementRecord{
        private String username;
        private List<Double> weights;
        private List<Double> heights;
        private List<LocalDate> dates;

        public BodyMeasurementRecord(String username, List<Double> weights, List<Double> heights, List<LocalDate> dates){
            this.username = username;
            this.weights = weights;
            this.heights = heights;
            this.dates = dates;
        }

        public List<Double> getWeights(){
            return weights;
        }

        public  List<Double> getHeights(){
            return heights;
        }

        public  List<LocalDate> getDates(){
            return dates;
        }

    }

    class UserInfo {
        private String username;
        private String name;
        private String email;
        private String password;
        private Double height;
        private Double weight;

        public UserInfo(String username, String password, String name, String email) {
            this(username, password, name, email, -1.0, -1.0);
        }

        public UserInfo(String username, String password, String name, String email, Double height, Double weight) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.email = email;
            this.height = height;
            this.weight = weight;
        }

        public String getUsername() {
            return username;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public Double getHeight() {
            return height;
        }

        public Double getWeight() {
            return weight;
        }

        public boolean hasBodyMeasurements() {
            return height != -1.0;
        }

    }

}
