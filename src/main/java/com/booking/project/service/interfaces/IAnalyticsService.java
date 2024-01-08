package com.booking.project.service.interfaces;

import com.booking.project.model.Analytics;
import com.booking.project.model.AnnualAnalytics;

import java.time.LocalDate;
import java.util.List;

public interface IAnalyticsService {
    AnnualAnalytics getAnnualAnalytics(int year, Long accommodationId, Long hostUserId);

    List<Analytics> getAnalyticsForAll(LocalDate startDate, LocalDate endDate, Long hostUserId);
}
