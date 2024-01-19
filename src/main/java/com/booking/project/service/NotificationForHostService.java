package com.booking.project.service;

import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.dto.NotificationForHostDTO;
import com.booking.project.model.Host;
import com.booking.project.model.NotificationForHost;
import com.booking.project.repository.inteface.IHostRepository;
import com.booking.project.repository.inteface.INotificationForHostRepository;
import com.booking.project.service.interfaces.INotificationForHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class NotificationForHostService implements INotificationForHostService {

    @Autowired
    private INotificationForHostRepository notificationForHostRepository;
    @Autowired
    private IHostRepository hostRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

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

        ArrayList<NotificationForHost> listToSort = new ArrayList<>(notificationsForHost);

        Collections.sort(listToSort, Comparator.comparing(NotificationForHost::getDateTime).reversed());
        notificationsForHost.clear();
        notificationsForHost.addAll(listToSort);

        return mapToDto(notificationsForHost);
    }

    @Override
    public NotificationForHost create(CreateNotificationForHostDTO createNotificationForHostDTO) throws Exception {

        NotificationForHost notificationForHost = new NotificationForHost();
        notificationForHost.setType(createNotificationForHostDTO.getType());
        notificationForHost.setDescription(createNotificationForHostDTO.getDescription());
        notificationForHost.setRead(false);
        LocalDateTime timeNow = LocalDateTime.now();
        notificationForHost.setDateTime(timeNow);

        Optional<Host> host = hostRepository.findById(createNotificationForHostDTO.getHostId());
        if (host.isEmpty()) return null;
        notificationForHost.setHost(host.get());

        save(notificationForHost);
        simpMessagingTemplate.convertAndSend("/socket-publisher/" + notificationForHost.getHost().getUser().getId(),notificationForHost);
        return notificationForHost;
    }
    @Override
    public NotificationForHostDTO markAsRead(Long id) {
        Optional<NotificationForHost> notificationForHost = notificationForHostRepository.findById(id);

        if(notificationForHost.isEmpty()){
            return null;
        }
        notificationForHost.get().setRead(true);
        notificationForHostRepository.save(notificationForHost.get());

        NotificationForHostDTO notificationForHostDTO = new NotificationForHostDTO(notificationForHost.get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        notificationForHostDTO.setDateTime(LocalDateTime.parse(formatter.format(notificationForHostDTO.getDateTime())));
        return notificationForHostDTO;

    }

    public Collection<NotificationForHostDTO> mapToDto(Collection<NotificationForHost> notificationsForHost){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        Collection<NotificationForHostDTO> notificationsForHostDTOS = new ArrayList<>();
        for(NotificationForHost notification: notificationsForHost){
            NotificationForHostDTO notificationDTO = new NotificationForHostDTO(notification);
            notificationDTO.setDateTime(LocalDateTime.parse(formatter.format(notificationDTO.getDateTime())));
            notificationsForHostDTOS.add(notificationDTO);
        }
        return notificationsForHostDTOS;
    }
}
