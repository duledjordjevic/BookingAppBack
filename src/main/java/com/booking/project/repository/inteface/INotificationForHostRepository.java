package com.booking.project.repository.inteface;


import com.booking.project.model.NotificationForHost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationForHost extends JpaRepository<NotificationForHost,Long> {
}
