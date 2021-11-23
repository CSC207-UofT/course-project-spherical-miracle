package User;

public interface UserOutputBoundary {
    void loginPrompt(String prompt);
    void loginMessage(boolean loggedIn);
    void logoutMessage(String name);
    void signupMessage(boolean signedUp);
    void scheduleList(String listSchedules);
}
