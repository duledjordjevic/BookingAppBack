package com.booking.project.service.interfaces;

import com.booking.project.dto.NotificationForGuestDTO;
import com.booking.project.model.NotificationForGuest;

import java.util.Collection;
import java.util.Optional;

public interface INotificationForGuestService {

    Collection<NotificationForGuest> findAll();

    Optional<NotificationForGuest> findById(Long id);

    NotificationForGuest save(NotificationForGuest notificationForGuest) throws Exception;

    void deleteById(Long id);

    NotificationForGuest update(NotificationForGuestDTO notificationForGuestDTO, Long id) throws Exception;

    Collection<NotificationForGuest> findByGuest(Long id);
}
