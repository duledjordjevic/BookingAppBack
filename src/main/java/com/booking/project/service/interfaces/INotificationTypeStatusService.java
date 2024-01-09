package com.booking.project.service.interfaces;

import com.booking.project.dto.NotificationForHostDTO;
import com.booking.project.model.NotificationTypeStatus;

import java.util.Collection;

public interface INotificationTypeStatusService {
    Collection<NotificationTypeStatus> findByUser(Long id);
}
