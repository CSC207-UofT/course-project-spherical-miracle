import User.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @BeforeEach
    void setUp() {

        user = new User("bob123", "password123", "Bob", "bob@gmail.com");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void passwordMatches() {
        assert user.passwordMatches("password123");
        assertFalse(user.passwordMatches("pass456"));
    }

    @Test
    void getName() {
        assert user.getName().equals("Bob");
    }

    @Test
    void getUsername() {
        assert user.getUsername().equals("bob123");
    }

    @Test
    void changePassword() {
        user.changePassword("password123", "123password");
        assert user.passwordMatches("123password");
        assert user.passwordMatches("123password");
    }
}