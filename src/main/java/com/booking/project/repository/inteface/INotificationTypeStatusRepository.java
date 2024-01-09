package com.booking.project.repository.inteface;

import com.booking.project.model.NotificationTypeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface INotificationTypeStatusRepository extends JpaRepository<NotificationTypeStatus, Long> {
    @Query("select n " +
            "from NotificationTypeStatus n " +
            "where n.user.id = :id")
    Collection<NotificationTypeStatus> findAllByUserId(@Param("id") Long id);
}
