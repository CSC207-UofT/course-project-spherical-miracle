package Domain.User.Entities;

import org.mindrot.jbcrypt.BCrypt;

/**
 * A user's account, which contains private details about the user.
 */
public class User {

    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final double weight;
    private final double height;

    /**
     * Constructs a User, giving them the name, username, email, and password.
     * @param name the name of the user
     * @param username the username of the user
     * @param email the email of the user
     * @param password the password of the user
     */
    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.weight = 0;
        this.height = 0;
    }

    /**
     * Constructs a User, giving them the name, username, email, and password.
     * @param name the name of the user
     * @param username the username of the user
     * @param email the email of the user
     * @param password the password of the user
     */
    public User(String username, String password, String name, String email, double height, double weight) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    /**
     * Returns the name of this User.
     * @return user's name
     **/
    public String getName() {
        return name;
    }

    /**
     * Returns the username of this User.
     * @return user's username
     **/
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email of this User.
     * @return user's email
     **/
    public String getEmail() {
        return email;
    }

    /**
     * Returns the weight of this User.
     * @return user's weight
     **/
    public double getWeight(){
        return weight;
    }

    /**
     * Sets the weight of this User.
     * @return user's current BMI, kg/m^2 (return error message if invalid input for height/weight).
     */
    public Object getBMI(){
        double output = this.weight / Math.pow(this.height, 2);
        if (Double.isNaN(output)) {
            return "Invalid entry. You have to add your Height/Weight in order to calculate your BMI.";
        }else{
            return output;
        }
    }

    /**
     * Returns the height of this User.
     * @return user's height
     */
    public double getHeight(){
        return height;
    }

    /**
     * Returns if tryPassword matches this User's password
     * @param tryPassword the password that the user input to attempt logging in
     * @return True iff tryPassword matches this User's password.
     */
    public boolean passwordMatches(String tryPassword) {
        return BCrypt.checkpw(tryPassword, password);
    }

    public String getPassword() {
        return password;
    }
}
