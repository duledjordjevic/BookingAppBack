package com.booking.project.repository.inteface;

import com.booking.project.model.NotificationForGuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationForGuestRepository extends JpaRepository<NotificationForGuest,Long> {
}
