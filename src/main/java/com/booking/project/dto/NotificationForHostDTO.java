package com.booking.project.dto;

import com.booking.project.model.NotificationForHost;
import com.booking.project.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationForHostDTO {

    private Long id;
    private NotificationType type;
    private String description;
    private boolean isRead;
    private LocalDateTime dateTime;

    public NotificationForHostDTO(NotificationForHost notificationForHost){
        this.id = notificationForHost.getId();
        this.type = notificationForHost.getType();
        this.description = notificationForHost.getDescription();
        this.isRead = notificationForHost.isRead();
        this.dateTime = notificationForHost.getDateTime();
    }
}
