package com.booking.project.repository.inteface;


import com.booking.project.model.Host;
import com.booking.project.model.NotificationForHost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface INotificationForHostRepository extends JpaRepository<NotificationForHost,Long> {

    Collection<NotificationForHost> findAllByHost(Optional<Host> host);
}
