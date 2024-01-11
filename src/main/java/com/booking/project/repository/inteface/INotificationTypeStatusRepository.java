package com.booking.project.repository.inteface;

import com.booking.project.model.NotificationTypeStatus;
import com.booking.project.model.enums.NotificationType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Modifying
    @Transactional
    @Query("Update NotificationTypeStatus n " +
            "set n.isTurned = :status " +
            "where n.user.id = :id and n.type = :notificationType")
    void updateNotificationType(@Param("status") Boolean status,
                                @Param("id") Long id,
                                @Param("notificationType") NotificationType notificationType);
}
