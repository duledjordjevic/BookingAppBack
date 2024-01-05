package com.booking.project.dto;

import com.booking.project.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationForHostDTO {

    private NotificationType type;
    private String description;
    private Long hostId;

}
