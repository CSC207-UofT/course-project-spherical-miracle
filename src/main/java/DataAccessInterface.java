public interface DataAccessInterface {
    String[] findUser(String username) throws UserDoesNotExistException;
    String[] loadSchedule();
    String[] loadUserScheduleCollection(String username);
    void saveUser(String username, String password, String name, String email);
    void saveSchedule(String schedule_name, Boolean is_public);
    void saveUserScheduleCollection();
    void editUser();
    //TODO: DO we want to have editSchedule()?
    void editUserScheduleCollection();

}
