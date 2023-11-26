package com.booking.project.service;

import com.booking.project.dto.CreateNotificationForGuestDTO;
import com.booking.project.dto.NotificationForGuestDTO;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.model.NotificationForGuest;
import com.booking.project.model.NotificationForHost;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.repository.inteface.IHostRepository;
import com.booking.project.repository.inteface.INotificationForGuestRepository;
import com.booking.project.service.interfaces.INotificationForGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NotificationForGuestService implements INotificationForGuestService {

    @Autowired
    private INotificationForGuestRepository notificationForGuestRepository;
    @Autowired
    private IGuestRepository guestRepository;

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

    @Override
    public NotificationForGuest update(NotificationForGuestDTO notificationForGuestDTO, Long id) throws Exception{
        Optional<NotificationForGuest> notificationForGuestForUpdate = findById(id);

        if(notificationForGuestForUpdate.isEmpty()) return null;

        notificationForGuestForUpdate.get().setId(notificationForGuestDTO.getId());
        notificationForGuestForUpdate.get().setDescription(notificationForGuestDTO.getDescription());

        Optional<Guest> guest = guestRepository.findById(notificationForGuestDTO.getGuestId());
        guest.ifPresent(value -> notificationForGuestForUpdate.get().setGuest(value));

        save(notificationForGuestForUpdate.get());
        return notificationForGuestForUpdate.get();
    }

    @Override
    public Collection<NotificationForGuest> findByGuest(Long id) {
        Optional<Guest> guest = guestRepository.findById(id);
        return notificationForGuestRepository.findAllByGuest(guest);
    }

    @Override
    public NotificationForGuest create(CreateNotificationForGuestDTO createNotificationForGuestDTO) throws Exception {

        NotificationForGuest notificationForGuest = new NotificationForGuest();
        notificationForGuest.setDescription(createNotificationForGuestDTO.getDescription());

        Optional<Guest> guest = guestRepository.findById(createNotificationForGuestDTO.getGuestId());
        if (guest.isEmpty()) return null;
        notificationForGuest.setGuest(guest.get());

        save(notificationForGuest);
        return notificationForGuest;
    }
}
