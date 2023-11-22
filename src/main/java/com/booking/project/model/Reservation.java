package com.booking.project.model;

import com.booking.project.model.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest guest;
    private double price;
    private int numberOfGuests;
    private Accomodation accomodation;
    private ReservationStatus status;

}
