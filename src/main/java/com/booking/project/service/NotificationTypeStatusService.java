package com.booking.project.service;

import com.booking.project.dto.NotificationTypeStatusDTO;
import com.booking.project.model.NotificationTypeStatus;
import com.booking.project.model.User;
import com.booking.project.model.enums.NotificationType;
import com.booking.project.repository.inteface.INotificationTypeStatusRepository;
import com.booking.project.service.interfaces.INotificationTypeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NotificationTypeStatusService implements INotificationTypeStatusService {
    @Autowired
    private INotificationTypeStatusRepository notificationTypeStatusRepository;

    @Override
    public Collection<NotificationTypeStatus> findByUser(Long id) {
        return notificationTypeStatusRepository.findAllByUserId(id);
    }
    @Override
    public void changeNotificationStatus(NotificationTypeStatusDTO notificationTypeStatusDTO){
        notificationTypeStatusRepository.updateNotificationType(notificationTypeStatusDTO.getIsTurned(),
                notificationTypeStatusDTO.getUserId(),notificationTypeStatusDTO.getType());
    }
    @Override
    public void initializeHostNotificationStatus(User user){
        NotificationTypeStatus notificationTypeStatusReserveReq = new NotificationTypeStatus(null,user,
                NotificationType.RESERVATION_REQUEST,true);
        NotificationTypeStatus notificationTypeStatusCreatedRes = new NotificationTypeStatus(null,user,
                NotificationType.CREATED_RESERVATION,true);
        NotificationTypeStatus notificationTypeStatusCancelledRes = new NotificationTypeStatus(null,user,
                NotificationType.CANCELLED_RESERVATION,true);
        NotificationTypeStatus notificationTypeStatusReview = new NotificationTypeStatus(null,user,
                NotificationType.NEW_REVIEW,true);
        notificationTypeStatusRepository.save(notificationTypeStatusReserveReq);
        notificationTypeStatusRepository.save(notificationTypeStatusCreatedRes);
        notificationTypeStatusRepository.save(notificationTypeStatusCancelledRes);
        notificationTypeStatusRepository.save(notificationTypeStatusReview);
    }
    @Override
    public void initializeGuestNotificationStatus(User user){
        NotificationTypeStatus notificationTypeStatus = new NotificationTypeStatus(null,user,
                NotificationType.RESERVATION_REQUEST_RESPOND,true);
        notificationTypeStatusRepository.save(notificationTypeStatus);
    }
    @Override
    public boolean findStatusByUserAndType(Long id, NotificationType type){
        return notificationTypeStatusRepository.findAllByUserAndType(id,type).getIsTurned();
    }
}
