package com.booking.project.service.interfaces;

import com.booking.project.dto.NotificationTypeStatusDTO;
import com.booking.project.model.NotificationTypeStatus;
import com.booking.project.model.User;
import com.booking.project.model.enums.NotificationType;

import java.util.Collection;

public interface INotificationTypeStatusService {
    Collection<NotificationTypeStatus> findByUser(Long id);

    void changeNotificationStatus(NotificationTypeStatusDTO notificationTypeStatusDTO);

    void initializeHostNotificationStatus(User user);

    void initializeGuestNotificationStatus(User user);

    boolean findStatusByUserAndType(Long id, NotificationType type);
}
