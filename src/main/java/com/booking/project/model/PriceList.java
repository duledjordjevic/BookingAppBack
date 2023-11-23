package com.booking.project.model;

import com.booking.project.model.enums.AccomodationStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double price;
    @Enumerated(EnumType.STRING)
    private AccomodationStatus status;
}
