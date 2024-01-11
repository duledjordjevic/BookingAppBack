package com.booking.project.dto;

import com.booking.project.validation.DateRangeConstraint;
import com.booking.project.validation.IdentityConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Data
@DateRangeConstraint
public class CreateReservationDTO {

    private LocalDate startDate;
    private LocalDate endDate;

    @Min(value = 1, message = "NumberOfGuests must be at least 1")
    @Max(value = 10, message = "NumberOfGuests cannot be more than 10")
    private int numberOfGuests;

    @IdentityConstraint
    private Long guestId;

    @IdentityConstraint
    private Long accommodationId;
}
