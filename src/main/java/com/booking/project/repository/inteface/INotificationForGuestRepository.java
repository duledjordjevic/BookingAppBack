package com.booking.project.repository.inteface;

import com.booking.project.model.Guest;
import com.booking.project.model.NotificationForGuest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface INotificationForGuestRepository extends JpaRepository<NotificationForGuest,Long> {

    Collection<NotificationForGuest> findAllByGuest(Optional<Guest> guest);
}
