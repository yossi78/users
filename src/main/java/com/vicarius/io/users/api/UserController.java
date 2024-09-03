package com.vicarius.io.users.api;
import com.vicarius.io.users.model.User;
import com.vicarius.io.users.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/quota")
    public Map<String, Integer> getUsersQuota() {
        return userService.getUsersQuota();
    }

    @PostMapping("/consume/{userId}")
    public String consumeQuota(@PathVariable String userId) {
        return userService.consumeQuota(userId);
    }
}
