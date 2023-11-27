package com.booking.project.dto;

import com.booking.project.model.NotificationForHost;
import com.booking.project.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationForHostDTO {

    private Long id;
    private NotificationType type;
    private String description;
    private Long hostId;

    public NotificationForHostDTO(NotificationForHost notificationForHost){
        this.id = notificationForHost.getId();
        this.type = notificationForHost.getType();
        this.description = notificationForHost.getDescription();
        this.hostId = notificationForHost.getHost().getId();
    }
}
