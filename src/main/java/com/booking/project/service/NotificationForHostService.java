package com.booking.project.service;

import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.dto.NotificationForGuestDTO;
import com.booking.project.dto.NotificationForHostDTO;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.model.NotificationForGuest;
import com.booking.project.model.NotificationForHost;
import com.booking.project.repository.inteface.IHostRepository;
import com.booking.project.repository.inteface.INotificationForHostRepository;
import com.booking.project.service.interfaces.INotificationForHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class NotificationForHostService implements INotificationForHostService {

    @Autowired
    private INotificationForHostRepository notificationForHostRepository;
    @Autowired
    private IHostRepository hostRepository;

    @Override
    public Collection<NotificationForHostDTO> findAll() {
        Collection<NotificationForHost> notificationsForHost = notificationForHostRepository.findAll();
        return mapToDto(notificationsForHost);
    }

    @Override
    public NotificationForHostDTO findById(Long id) {
        Optional<NotificationForHost> notificationForHost = notificationForHostRepository.findById(id);
        if(notificationForHost.isEmpty()) return null;

        return new NotificationForHostDTO(notificationForHost.get());
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
    public Collection<NotificationForHostDTO> findByHost(Long id) {
        Host host = hostRepository.findByUserId(id);
        Collection<NotificationForHost> notificationsForHost = notificationForHostRepository.findAllByHost(host);
        return mapToDto(notificationsForHost);
    }

    @Override
    public NotificationForHost create(CreateNotificationForHostDTO createNotificationForHostDTO) throws Exception {

        NotificationForHost notificationForHost = new NotificationForHost();
        notificationForHost.setType(createNotificationForHostDTO.getType());
        notificationForHost.setDescription(createNotificationForHostDTO.getDescription());
        notificationForHost.setRead(false);

        Optional<Host> host = hostRepository.findById(createNotificationForHostDTO.getHostId());
        if (host.isEmpty()) return null;
        notificationForHost.setHost(host.get());

        save(notificationForHost);
        return notificationForHost;
    }

    public Collection<NotificationForHostDTO> mapToDto(Collection<NotificationForHost> notificationsForHost){
        Collection<NotificationForHostDTO> notificationsForHostDTOS = new ArrayList<>();
        for(NotificationForHost notification: notificationsForHost){
            NotificationForHostDTO notificationDTO = new NotificationForHostDTO(notification);
            notificationsForHostDTOS.add(notificationDTO);
        }
        return notificationsForHostDTOS;
    }
}
