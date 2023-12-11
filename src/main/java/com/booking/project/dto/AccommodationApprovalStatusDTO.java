package com.booking.project.dto;

import com.booking.project.model.enums.AccommodationApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationApprovalStatusDTO {
    private AccommodationApprovalStatus approvalStatus;
}
