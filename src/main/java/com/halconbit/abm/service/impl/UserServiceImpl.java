package com.halconbit.abm.service.impl;

import com.halconbit.abm.entity.User;
import com.halconbit.abm.repository.UserRepository;
import com.halconbit.abm.repository.jpa.JpaUserRepository;
import com.halconbit.abm.service.NotificationService;
import com.halconbit.abm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @Override
    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        notificationService.sendNewUserNotification(user);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, String name, String email) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(name);
            user.setEmail(email);
            notificationService.sendUserUpdatedNotification(user);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        notificationService.sendUserDeletedNotification(user);
        userRepository.deleteById(id);
    }
}