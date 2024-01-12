package com.booking.project.dto;

import com.booking.project.model.enums.NotificationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private NotificationType type;

    @NotEmpty
    private String description;

    @NotNull
    private Long hostId;

}
