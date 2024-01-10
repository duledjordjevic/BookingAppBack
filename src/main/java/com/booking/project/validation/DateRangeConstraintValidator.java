package com.booking.project.validation;

import com.booking.project.dto.CreateReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateRangeConstraintValidator implements ConstraintValidator<DateRangeConstraint, CreateReservationDTO> {

    @Override
    public void initialize(DateRangeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateReservationDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        LocalDate startDate = value.getStartDate();
        LocalDate endDate = value.getEndDate();

        return startDate == null || endDate == null || startDate.isBefore(endDate);
    }
}
