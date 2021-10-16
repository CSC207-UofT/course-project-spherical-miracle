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

    public User(String name, String username, String email, String password) {
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

    // TODO: is getPassword necessary?
    public String getPassword() {
        return password;
    }

    /**
     * Return if tryPassword matches this User's password
     * @param password
     * @return True iff tryPassword matches this User's password.
     */
    public boolean passwordMatches(String tryPassword) {
        return password.equals(tryPassword);
    }
    /**
     * Change this User's password to newPassword.
     *
     * @param newPassword the new password for this User
     **/
    public void changePassword(String newPassword) {
        password = newPassword;
    }

}