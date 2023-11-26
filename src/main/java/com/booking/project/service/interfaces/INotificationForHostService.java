package com.booking.project.service.interfaces;

import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.dto.NotificationForHostDTO;
import com.booking.project.model.NotificationForHost;

import java.util.Collection;
import java.util.Optional;

public interface INotificationForHostService {

    Collection<NotificationForHost> findAll();

    Optional<NotificationForHost> findById(Long id);

    NotificationForHost save(NotificationForHost notificationForHost) throws Exception;

    void deleteById(Long id);

    NotificationForHost update(NotificationForHostDTO notificationForHostDTO, Long id) throws Exception;
    Collection<NotificationForHost> findByHost(Long id);
    NotificationForHost create(CreateNotificationForHostDTO createNotificationForHostDTO) throws Exception;
}
