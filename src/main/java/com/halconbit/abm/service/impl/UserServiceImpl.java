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
    private final UserRepository userRepository; // Repositorio para acceder a los usuarios
    private final NotificationService notificationService; // Servicio de notificaciones

    @Autowired
    public UserServiceImpl(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository; // Inyecta el repositorio de usuarios
        this.notificationService = notificationService; // Inyecta el servicio de notificaciones
    }

    @Override
    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        notificationService.sendNewUserNotification(user); // Envía una notificación sobre el nuevo usuario
        return userRepository.save(user); // Guarda el usuario en el repositorio
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id); // Obtiene un usuario del repositorio por su ID
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Obtiene todos los usuarios del repositorio
    }

    @Override
    public User updateUser(Long id, String name, String email) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(name);
            user.setEmail(email);
            notificationService.sendUserUpdatedNotification(user); // Envía una notificación sobre la actualización del usuario
            return userRepository.save(user); // Guarda los cambios del usuario en el repositorio
        }
        throw new RuntimeException("User not found"); // Lanza una excepción si el usuario no se encuentra
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        notificationService.sendUserDeletedNotification(user); // Envía una notificación sobre la eliminación del usuario
        userRepository.deleteById(id); // Elimina el usuario del repositorio
    }
}