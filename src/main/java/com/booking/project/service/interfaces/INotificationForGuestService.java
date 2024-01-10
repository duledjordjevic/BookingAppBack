package com.booking.project.service.interfaces;

import com.booking.project.dto.CreateNotificationForGuestDTO;
import com.booking.project.dto.NotificationForGuestDTO;
import com.booking.project.model.NotificationForGuest;

import java.util.Collection;

public interface INotificationForGuestService {

    Collection<NotificationForGuestDTO> findAll();
    NotificationForGuestDTO findById(Long id);
    NotificationForGuest save(NotificationForGuest notificationForGuest) throws Exception;
    void deleteById(Long id);
    Collection<NotificationForGuestDTO> findByGuest(Long id);
    NotificationForGuest create(CreateNotificationForGuestDTO createNotificationForGuestDTO) throws Exception;

    NotificationForGuestDTO markAsRead(Long id);
}
