package com.booking.project.dto;

import com.booking.project.model.Accommodation;
import com.booking.project.model.Guest;
import com.booking.project.model.Reservation;
import com.booking.project.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private int numberOfGuests;
    private ReservationStatus status;
    private Guest guest;
    private AccommodationDTO accommodation;
    private boolean isHostReported;

    public ReservationDTO(Reservation reservation){
        this.id = reservation.getId();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.price = reservation.getPrice();
        this.numberOfGuests = reservation.getNumberOfGuests();
        this.status = reservation.getStatus();
        this.accommodation = new AccommodationDTO(reservation.getAccommodation());
        this.guest = reservation.getGuest();
        this.accommodation.setHost(reservation.getAccommodation().getHost());
        this.isHostReported = reservation.isHostReported();
    }
}
