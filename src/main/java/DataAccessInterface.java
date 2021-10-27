public interface DataAccessInterface {
    String[] findUser(String username);
    String[] loadSchedule();
    void saveUser(String[] string);
    void saveSchedule();
}
