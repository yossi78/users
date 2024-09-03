package com.vicarius.io.users.service;
import com.vicarius.io.users.model.User;
import com.vicarius.io.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private Map<String, Integer> userQuota = new HashMap<>();
    private static final int MAX_QUOTA = 5;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CRUD Operations
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(String userId, User updatedUser) {
        if (userRepository.existsById(userId)) {
            updatedUser.setId(userId);
            return userRepository.save(updatedUser);
        }
        return null;
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
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

    public void handleElasticSearchOperations() {
        System.out.println("Handling ElasticSearch operations (print-only)");
    }

    public void handleMySqlOperations() {
        System.out.println("Handling MySQL operations");
    }

    public void handleDataSource() {
        LocalTime now = LocalTime.now();
        LocalTime startOfDay = LocalTime.of(9, 0);
        LocalTime endOfDay = LocalTime.of(17, 0);

        if (now.isAfter(startOfDay) && now.isBefore(endOfDay)) {
            handleMySqlOperations();
        } else {
            handleElasticSearchOperations();
        }
    }
}
