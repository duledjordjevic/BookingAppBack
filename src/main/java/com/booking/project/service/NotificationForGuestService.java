package com.booking.project.service;

import com.booking.project.model.NotificationForGuest;
import com.booking.project.repository.inteface.INotificationForGuestRepository;
import com.booking.project.service.interfaces.INotificationForGuestService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NotificationForGuestService implements INotificationForGuestService {

    private INotificationForGuestRepository notificationForGuestRepository;

    @Override
    public Collection<NotificationForGuest> findAll() {
        return notificationForGuestRepository.findAll();
    }

    @Override
    public Optional<NotificationForGuest> findById(Long id) {
        return notificationForGuestRepository.findById(id);
    }

    @Override
    public NotificationForGuest save(NotificationForGuest notificationForGuest) throws Exception {
        return notificationForGuestRepository.save(notificationForGuest);
    }

    @Override
    public void deleteById(Long id) {
        notificationForGuestRepository.deleteById(id);
    }
}
