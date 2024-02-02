package com.halconbit.abm.service;

import com.halconbit.abm.entity.User;

public interface NotificationService {
    void sendNewUserNotification(User user);
    void sendUserDeletedNotification(User user);
    void sendUserUpdatedNotification(User user);
}
