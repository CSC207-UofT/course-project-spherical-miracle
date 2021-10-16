import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        user = new User("Bob", "bob123", "bob@gmail.com", "password123");
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
    void getUsername() {
        assert user.getUsername().equals("bob123");
    }

    @Test
    void changePassword() {
        user.changePassword("123password");
        assert user.passwordMatches("123password");
    }
}