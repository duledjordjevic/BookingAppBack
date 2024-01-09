package com.booking.project.service;

import com.booking.project.model.NotificationTypeStatus;
import com.booking.project.repository.inteface.INotificationTypeStatusRepository;
import com.booking.project.service.interfaces.INotificationTypeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NotificationTypeStatusService implements INotificationTypeStatusService {
    @Autowired
    private INotificationTypeStatusRepository notificationTypeStatusRepository;

    @Override
    public Collection<NotificationTypeStatus> findByUser(Long id) {
        return notificationTypeStatusRepository.findAllByUserId(id);
    }
}
