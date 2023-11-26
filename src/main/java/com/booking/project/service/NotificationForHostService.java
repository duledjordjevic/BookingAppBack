package com.booking.project.service;

import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.dto.NotificationForHostDTO;
import com.booking.project.model.Host;
import com.booking.project.model.NotificationForHost;
import com.booking.project.repository.inteface.IHostRepository;
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
    @Autowired
    private IHostRepository hostRepository;

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

        Optional<Host> host = hostRepository.findById(notificationForHostDTO.getHostId());
        host.ifPresent(value -> notificationForHostForUpdate.get().setHost(value));

        save(notificationForHostForUpdate.get());
        return notificationForHostForUpdate.get();
    }

    @Override
    public Collection<NotificationForHost> findByHost(Long id) {
        Optional<Host> host = hostRepository.findById(id);
        return notificationForHostRepository.findAllByHost(host);
    }

    @Override
    public NotificationForHost create(CreateNotificationForHostDTO createNotificationForHostDTO) throws Exception {

        NotificationForHost notificationForHost = new NotificationForHost();
        notificationForHost.setType(createNotificationForHostDTO.getType());
        notificationForHost.setDescription(createNotificationForHostDTO.getDescription());

        Optional<Host> host = hostRepository.findById(createNotificationForHostDTO.getHostId());
        if (host.isEmpty()) return null;
        notificationForHost.setHost(host.get());

        save(notificationForHost);
        return notificationForHost;
    }
}
