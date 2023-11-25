package com.booking.project.service;

import com.booking.project.model.NotificationForHost;
import com.booking.project.repository.inteface.INotificationForHostRepository;
import com.booking.project.service.interfaces.INotificationForHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NotificationForHostService implements INotificationForHostService {

    @Autowired
    private INotificationForHostRepository notificationForHostRepository;

    @Override
    public Collection<NotificationForHost> findAll() {
        return notificationForHostRepository.findAll();
    }

    @Override
    public Optional<NotificationForHost> findById(Long id) {
        return notificationForHostRepository.findById(id);
    }

    @Override
    public NotificationForHost save(NotificationForHost notificationForHost) throws Exception {
        return notificationForHostRepository.save(notificationForHost);
    }

    @Override
    public void deleteById(Long id) {
        notificationForHostRepository.deleteById(id);
    }
}
