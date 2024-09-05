package com.vicarius.io.users.repository;

import com.vicarius.io.users.util.TimeUtil;
import com.vicarius.io.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryManager {
    private final UserRepository userRepository;



    @Autowired
    public UserRepositoryManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  User save(User user){
        if(TimeUtil.isDayTime()) {
            return userRepository.save(user);
        }else {
            System.out.println("PERSIST USER TO ELASTIC SEARCH");
            return user;
        }
    }


    public  User findById(String userId){
        if(TimeUtil.isDayTime()) {
            return userRepository.findById(userId).orElse(null);
        }else {
            System.out.println("FIND USER TO ELASTIC SEARCH");
            return null;
        }
    }

    public  User existById(String userId, User updatedUser) {
        if(!TimeUtil.isDayTime()) {
            System.out.println("EXIST BY ELASTIC SEARCH");
            return null;
        }
        if (userRepository.existsById(userId)) {
            updatedUser.setId(userId);
            return userRepository.save(updatedUser);
        }
        return null;
    }


    public void deleteById(String userId) {
        if(!TimeUtil.isDayTime()) {
            System.out.println("DELETE BY ELASTIC SEARCH");
        }else {
            userRepository.deleteById(userId);
        }
    }


}
