public interface DataAccessInterface {
    Object findUser(String username);
    String[] loadSchedule();
    void saveUser(String[] string);
    void saveSchedule();
}
