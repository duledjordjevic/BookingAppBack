package com.booking.project.dto;

import com.booking.project.model.NotificationForGuest;
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
public class NotificationForGuestDTO {

    private Long id;
    private String description;
    private boolean isRead;
    private LocalDateTime dateTime;

    public NotificationForGuestDTO(NotificationForGuest notificationForGuest){
        this.id = notificationForGuest.getId();
        this.description = notificationForGuest.getDescription();
        this.isRead = notificationForGuest.isRead();
        this.dateTime = notificationForGuest.getDateTime();
    }
}
