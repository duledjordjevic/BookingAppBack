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
    private String reason;
    private UserInfoDTO reportedUser;
    private UserInfoDTO reportingUser;
    private String accommodationTitle;
    private LocalDate startDate;
    private LocalDate endDate;

}
