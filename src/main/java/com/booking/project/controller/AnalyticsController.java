package com.booking.project.controller;

import com.booking.project.model.Analytics;
import com.booking.project.model.AnnualAnalytics;
import com.booking.project.service.interfaces.IAnalyticsService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private IAnalyticsService analyticsService;


    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value = "/annualAnalytics/{year}/{accommodationId}/{hostUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnnualAnalytics(@PathVariable int year, @PathVariable Long accommodationId, @PathVariable Long hostUserId){
        AnnualAnalytics annualAnalytics = analyticsService.getAnnualAnalytics(year, accommodationId, hostUserId);

        return new ResponseEntity<AnnualAnalytics>(annualAnalytics, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value = "/allAccommodations/{hostUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnalyticsForAll(@PathParam("startDate") LocalDate startDate, @PathParam("endDate") LocalDate endDate, @PathVariable Long hostUserId){
        List<Analytics> analytics = analyticsService.getAnalyticsForAll(startDate, endDate, hostUserId);

        return new ResponseEntity<Collection<Analytics>>(analytics, HttpStatus.OK);
    }
}
