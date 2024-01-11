package com.booking.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReportDTO {
    private Long id;
    private Long userId;
    private String reason;
    private Long reservationId;
}
