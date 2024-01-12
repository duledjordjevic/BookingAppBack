package com.booking.project.dto;

import com.booking.project.validation.IdentityConstraint;
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
public class UserReportDTO {
    private Long id;

    @IdentityConstraint
    private Long userReportedId;

    @IdentityConstraint
    private Long userReportingId;

    @NotEmpty
    private String reason;
    private Long reservationId;
}
