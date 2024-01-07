package com.booking.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Analytics {

    private Long accommodationId;
    private String name;
    public Long[] reservationsPerMonth;
    public double[] earningsPerMonth;
    private Long totalReservations;
    private double totalEarnings;

    public Analytics(){
        reservationsPerMonth = new Long[12];
        Arrays.fill(reservationsPerMonth, 0L);

        earningsPerMonth = new double[12];
        Arrays.fill(earningsPerMonth, 0);
    }
}
