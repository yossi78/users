package com.vicarius.io.users.service;
import com.vicarius.io.users.model.User;
import com.vicarius.io.users.repository.UserRepositoryManager;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepositoryManager userRepositoryManager;
    private Map<String, Integer> userQuota = new HashMap<>();
    private static final int MAX_QUOTA = 5;

    public UserService(UserRepositoryManager userRepositoryManager) {
        this.userRepositoryManager = userRepositoryManager;
    }

    // CRUD Operations
    public User createUser(User user) {
        return userRepositoryManager.save(user);
    }

    public User getUser(String userId) {
        return userRepositoryManager.findById(userId);
    }

    public User updateUser(String userId, User updatedUser) {
        return userRepositoryManager.existById(userId,updatedUser);
    }

    public void deleteUser(String userId) {
        userRepositoryManager.deleteById(userId);
    }

    // Quota Functions
    public String consumeQuota(String userId) {
        if (!userQuota.containsKey(userId)) {
            userQuota.put(userId, 0);
        }

        int currentQuota = userQuota.get(userId);
        if (currentQuota >= MAX_QUOTA) {
            return "Quota exceeded for user " + userId;
        } else {
            userQuota.put(userId, currentQuota + 1);
            return "Quota consumed for user " + userId;
        }
    }

    public Map<String, Integer> getUsersQuota() {
        return userQuota;
    }


}
