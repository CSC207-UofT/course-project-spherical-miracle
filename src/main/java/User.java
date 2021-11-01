public class User {

    private String name;
    private String username;
    private String password;
    private String email;

    /**
     * Construct a User, giving them the name, username, email, and password.
     *
     * @param name the name of the User
     * @param username the username of the User
     * @param email the email of the User
     * @param password the password of the User
     */

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    /**
     * Return the name of this User.
     *
     * @return
     **/
    public String getName() {
        return name;
    }

    /**
     * Return the username of this User.
     *
     * @return
     **/
    public String getUsername() {
        return username;
    }

    /**
     * Return the email of this User.
     *
     * @return
     **/
    public String getEmail() {
        return email;
    }

    /**
     * Return if tryPassword matches this User's password
     * @param tryPassword
     * @return True iff tryPassword matches this User's password.
     */
    public boolean passwordMatches(String tryPassword) {
        return password.equals(tryPassword);
    }
    /**
     * Change this User's password to newPassword if the oldPassword matches the user's current password.
     * Return true iff password is successfully changed.
     *
     * @param newPassword the new password for this User
     * @return
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