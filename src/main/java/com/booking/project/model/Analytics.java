package com.booking.project.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Analytics {
    private Long accommodationId;
    private String name;
    private Long totalReservations;
    private double totalEarnings;
}
