package com.booking.project.service.interfaces;

import com.booking.project.model.CommentAboutHost;
import com.booking.project.model.NotificationForGuest;

import java.util.Collection;
import java.util.Optional;

public interface INotificationForGuestService {

    Collection<NotificationForGuest> findAll();

    Optional<NotificationForGuest> findById(Long id);

    NotificationForGuest save(NotificationForGuest notificationForGuest) throws Exception;

    void deleteById(Long id);
}