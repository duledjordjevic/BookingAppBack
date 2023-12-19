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
public class IntervalPriceDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
}
