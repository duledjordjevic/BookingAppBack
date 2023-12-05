package com.booking.project.dto;

import com.booking.project.model.NotificationForGuest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationForGuestDTO {

    private Long id;
    private String description;

    public NotificationForGuestDTO(NotificationForGuest notificationForGuest){
        this.id = notificationForGuest.getId();
        this.description = notificationForGuest.getDescription();
    }
}
