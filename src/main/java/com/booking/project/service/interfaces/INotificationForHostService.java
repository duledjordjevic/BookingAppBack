package com.booking.project.service.interfaces;

import com.booking.project.model.NotificationForGuest;
import com.booking.project.model.NotificationForHost;

import java.util.Collection;
import java.util.Optional;

public interface INotificationForHostService {

    Collection<NotificationForHost> findAll();

    Optional<NotificationForHost> findById(Long id);

    NotificationForHost save(NotificationForHost notificationForHost) throws Exception;

    void deleteById(Long id);
}
