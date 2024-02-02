package com.halconbit.abm.service.impl;

import com.halconbit.abm.entity.User;
import com.halconbit.abm.service.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private SimpMessagingTemplate messagingTemplate;

    public NotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendNewUserNotification(User user) {
        String message = "A new user has been created: " + user.getName();
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }

    @Override
    public void sendUserDeletedNotification(User user) {
        String message = "A user has been deleted: " + user.getName();
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }

    @Override
    public void sendUserUpdatedNotification(User user) {
        String message = "A user has been updated: " + user.getName();
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}