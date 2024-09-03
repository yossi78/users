package com.vicarius.io.users;



import com.vicarius.io.users.model.User;
import com.vicarius.io.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuotaApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void testUserQuota() {
        User user = new User("1", "John", "Doe", null);
        userService.createUser(user);

        assertEquals("Quota consumed for user 1", userService.consumeQuota("1"));
        assertEquals("Quota consumed for user 1", userService.consumeQuota("1"));
        assertEquals("Quota consumed for user 1", userService.consumeQuota("1"));
        assertEquals("Quota consumed for user 1", userService.consumeQuota("1"));
        assertEquals("Quota consumed for user 1", userService.consumeQuota("1"));
        assertEquals("Quota exceeded for user 1", userService.consumeQuota("1"));
    }

    @Test
    void testGetUsersQuota() {
        Map<String, Integer> quota = userService.getUsersQuota();
        assertTrue(quota.containsKey("1"));
    }
}
