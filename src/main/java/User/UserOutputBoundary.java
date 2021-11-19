package User;

public interface UserOutputBoundary {
    void loginMessage(boolean loggedIn);
    void logoutMessage(String name);
    void signupMessage(boolean signedUp);
}
