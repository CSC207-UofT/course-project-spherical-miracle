package User.Entities;

import org.mindrot.jbcrypt.BCrypt;

/**
 * A user's account, which contains private details about the user.
 */
public class User {

    private String name;
    private String username;
    private String password;
    private String email;

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
     * Returns if tryPassword matches this User's password
     * @param tryPassword the password that the user input to attempt logging in
     * @return True iff tryPassword matches this User's password.
     */
    public boolean passwordMatches(String tryPassword) {
        return BCrypt.checkpw(tryPassword, password);
    }

    /**
     * Changes this User's password to newPassword if the oldPassword matches the user's current password.
     * Return true iff password is successfully changed.
     * @param newPassword the new password for this User
     * @return whether the password was successfully changed or not
     **/
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.passwordMatches(oldPassword)) {
            password = newPassword;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }
}
