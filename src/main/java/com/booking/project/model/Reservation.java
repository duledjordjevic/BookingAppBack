package com.booking.project.model;

import com.booking.project.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Guest guest;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int numberOfGuests;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Accommodation accommodation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public void copyValues(Reservation reservation){
        this.startDate = reservation.startDate;
        this.endDate = reservation.endDate;
        this.guest = reservation.guest;
        this.price = reservation.price;
        this.numberOfGuests = reservation.numberOfGuests;
        this.accommodation = reservation.accommodation;
        this.status = reservation.status;
    }
}
