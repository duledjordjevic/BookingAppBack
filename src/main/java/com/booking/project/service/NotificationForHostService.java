package com.booking.project.service;

import com.booking.project.dto.NotificationForHostDTO;
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

    @Override
    public NotificationForHost update(NotificationForHostDTO notificationForHostDTO, Long id) throws Exception{
        Optional<NotificationForHost> notificationForHostForUpdate = findById(id);

        if(notificationForHostForUpdate.isEmpty()) return null;

        notificationForHostForUpdate.get().setId(notificationForHostDTO.getId());
        notificationForHostForUpdate.get().setDescription(notificationForHostDTO.getDescription());
        notificationForHostForUpdate.get().setType(notificationForHostDTO.getType());
        notificationForHostForUpdate.get().getHost().copyValues(notificationForHostDTO.getHostDTO());

        save(notificationForHostForUpdate.get());
        return notificationForHostForUpdate.get();
    }
}
