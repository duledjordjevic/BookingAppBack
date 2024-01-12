package com.booking.project.service;

import com.booking.project.dto.CreateNotificationForGuestDTO;
import com.booking.project.dto.NotificationForGuestDTO;
import com.booking.project.dto.NotificationForHostDTO;
import com.booking.project.model.*;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.repository.inteface.INotificationForGuestRepository;
import com.booking.project.service.interfaces.INotificationForGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class NotificationForGuestService implements INotificationForGuestService {

    @Autowired
    private INotificationForGuestRepository notificationForGuestRepository;
    @Autowired
    private IGuestRepository guestRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public Collection<NotificationForGuestDTO> findAll() {
        Collection<NotificationForGuest> notificationsForGuest = notificationForGuestRepository.findAll();
        return mapToDto(notificationsForGuest);
    }

    @Override
    public NotificationForGuestDTO findById(Long id) {
        Optional<NotificationForGuest> notificationForGuest = notificationForGuestRepository.findById(id);
        if(notificationForGuest.isEmpty()) return null;

        return new NotificationForGuestDTO(notificationForGuest.get());
    }

    @Override
    public NotificationForGuest save(NotificationForGuest notificationForGuest) throws Exception {
        return notificationForGuestRepository.save(notificationForGuest);
    }

    @Override
    public void deleteById(Long id) {
        notificationForGuestRepository.deleteById(id);
    }

    @Override
    public Collection<NotificationForGuestDTO> findByGuest(Long id) {
        Optional<Guest> guest = guestRepository.findByUserId(id);
        Collection<NotificationForGuest> notificationsForGuest = notificationForGuestRepository.findAllByGuest(guest);

        ArrayList<NotificationForGuest> listToSort = new ArrayList<>(notificationsForGuest);

        Collections.sort(listToSort, Comparator.comparing(NotificationForGuest::getDateTime).reversed());
        notificationsForGuest.clear();
        notificationsForGuest.addAll(listToSort);

        return mapToDto(notificationsForGuest);
    }

    @Override
    public NotificationForGuest create(CreateNotificationForGuestDTO createNotificationForGuestDTO) throws Exception {

        NotificationForGuest notificationForGuest = new NotificationForGuest();
        notificationForGuest.setDescription(createNotificationForGuestDTO.getDescription());

        Optional<Guest> guest = guestRepository.findById(createNotificationForGuestDTO.getGuestId());
        if (guest.isEmpty()) return null;
        notificationForGuest.setGuest(guest.get());
        notificationForGuest.setDateTime(LocalDateTime.now());
        notificationForGuest.setRead(false);

        save(notificationForGuest);
        simpMessagingTemplate.convertAndSend("/socket-publisher/" + notificationForGuest.getGuest().getUser().getId(),notificationForGuest);
        return notificationForGuest;
    }
    @Override
    public NotificationForGuestDTO markAsRead(Long id) {
        Optional<NotificationForGuest> notificationForGuest = notificationForGuestRepository.findById(id);

        if(notificationForGuest.isEmpty()){
            return null;
        }
        notificationForGuest.get().setRead(true);
        notificationForGuestRepository.save(notificationForGuest.get());

        NotificationForGuestDTO notificationForGuestDTO = new NotificationForGuestDTO(notificationForGuest.get());
        return notificationForGuestDTO;

    }

    public Collection<NotificationForGuestDTO> mapToDto(Collection<NotificationForGuest> notificationsForGuest){
        Collection<NotificationForGuestDTO> notificationsForGuestDTOS = new ArrayList<>();
        for(NotificationForGuest notification: notificationsForGuest){
            NotificationForGuestDTO notificationDTO = new NotificationForGuestDTO(notification);
            notificationsForGuestDTOS.add(notificationDTO);
        }
        return notificationsForGuestDTOS;
    }
}
