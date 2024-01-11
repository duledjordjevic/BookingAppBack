package com.booking.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBlockDTO {
    private Long id;
    private Long userId;
    private String reason;
    private Long reservationId;
    private String name;
    private String lastName;
    private String accomodationTitle;
    private LocalDate startDate;
    private LocalDate endDate;

}
