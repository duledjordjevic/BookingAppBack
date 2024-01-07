package com.booking.project.service.interfaces;

import com.booking.project.model.Analytics;

import java.util.List;

public interface IAnalyticsService {
    Analytics getAnnualAnalytics(int year, Long accommodationId, Long hostUserId);
}
