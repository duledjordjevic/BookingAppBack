package com.booking.project.dto;

import com.booking.project.model.User;
import com.booking.project.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTypeStatusDTO {
    private Long id;
    private Long userId;
    private NotificationType type;
    private Boolean isTurned;
}
