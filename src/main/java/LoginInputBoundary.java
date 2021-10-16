/**
 * Input boundary for the login use case.
 */
// TODO: resolve differences between LoginInputBoundary and LoginUseCase
public interface LoginInputBoundary {
    boolean logIn(String username, String password);
}
