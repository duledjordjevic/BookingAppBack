package com.booking.project.dto;

import com.booking.project.model.User;
import com.booking.project.model.enums.NotificationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Long userId;

    @NotEmpty
    private NotificationType type;

    @NotNull
    private Boolean isTurned;
}
