package com.booking.project.model;

import com.booking.project.model.enums.AccomodationType;
import com.booking.project.model.enums.Amenities;
import com.booking.project.model.enums.CancellationPolicy;
import com.booking.project.model.enums.ReservationMethod;

import java.util.List;

public class Accomodation {
    private String title;
    private String description;
    private Address location;
    private List<Amenities> amenities;
    private Long minGuests;
    private Long maxGuests;
    private AccomodationType type;
    private CancellationPolicy cancellationPolicy;
    private boolean isAvailableForReservation;
    private double raiting;
    private ReservationMethod reservationMethod;

}
