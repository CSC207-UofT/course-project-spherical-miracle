/**
 * Input boundary for the login use case.
 */
public interface LoginInputBoundary {
    LoginUseCase.LoginResult login(String username, String password);
}
