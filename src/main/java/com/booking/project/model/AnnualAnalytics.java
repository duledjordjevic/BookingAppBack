package com.booking.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class AnnualAnalytics extends  Analytics{

    public Long[] reservationsPerMonth;
    public double[] earningsPerMonth;

    public AnnualAnalytics(){
        reservationsPerMonth = new Long[12];
        Arrays.fill(reservationsPerMonth, 0L);

        earningsPerMonth = new double[12];
        Arrays.fill(earningsPerMonth, 0);
    }
}
