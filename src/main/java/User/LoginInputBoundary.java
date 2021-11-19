package User;

/**
 * Input boundary for the login use case.
 */
public interface LoginInputBoundary {

    /**
     * Returns if a login with the given credentials is successful.
     * @param username - username of the user
     * @param password - password of the user.
     * @return an enum LoginResult describing the login result.
     * SUCCESS - login was successful
     * INCORRECT_PASSWORD - incorrect credentials
     * NO_SUCH_USER - no user with given username
     */
    LoginUseCase.LoginResult login(String username, String password);
}
