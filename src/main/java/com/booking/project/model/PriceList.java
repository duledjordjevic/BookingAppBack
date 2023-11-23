package com.booking.project.model;

import com.booking.project.model.enums.AccomodationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class PriceList {

    @Id
    private Long id;
    private LocalDate date;
    private double price;
    private AccomodationStatus status;
}
