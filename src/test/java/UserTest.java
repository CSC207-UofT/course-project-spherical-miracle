import Domain.User.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    User user2;

    @BeforeEach
    void setUp() {

        user = new User("bob123", "password123", "Bob", "bob@gmail.com", 173, 66);
        user2 = new User("pirooz321", "password321", "Pirooz", "pirooz@gmail.com");
    }

    @Test
    void getName() {
        assert user.getName().equals("Bob");
        assert user2.getName().equals("Pirooz");
    }

    @Test
    void getUsername() {
        assert user.getUsername().equals("bob123");
        assert user2.getUsername().equals("pirooz321");
    }

    @Test
    void getEmail() {
        assert user.getEmail().equals("bob@gmail.com");
        assert user2.getEmail().equals("pirooz@gmail.com");
    }

    @Test
    void getWeight() { assert user.getWeight() == 66; }

    @Test
    void getBMI() {
        assert user.getBMI().equals(66 / Math.pow(173, 2));
        assert user2.getBMI().equals("Invalid entry. You have to add your Height/Weight in order to calculate your BMI.");
    }

    @Test
    void getHeight() { assert user.getHeight() == 173; }

    @Test
    void passwordMatches() {
        String pwHash = BCrypt.hashpw("password123", BCrypt.gensalt(10));
        String wrongHash = BCrypt.hashpw("password456", BCrypt.gensalt(10));
        assert user.passwordMatches(pwHash);
        assert !(user.passwordMatches(wrongHash));
    }

    @Test
    void getPassword() {
        assert user.getPassword().equals("password123");
    }
}