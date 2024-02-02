package com.halconbit.abm.service;

import com.halconbit.abm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String name, String email);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, String name, String email);
    void deleteUser(Long id);
}