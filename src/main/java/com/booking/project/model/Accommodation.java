package com.booking.project.model;

import com.booking.project.model.enums.AccomodationType;
import com.booking.project.model.enums.Amenities;
import com.booking.project.model.enums.CancellationPolicy;
import com.booking.project.model.enums.ReservationMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private List<Amenities> amenities;

    @Column(nullable = false)
    private Long minGuests;

    @Column(nullable = false)
    private Long maxGuests;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccomodationType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CancellationPolicy cancellationPolicy;

    @Column(nullable = false)
    private boolean isAvailableForReservation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationMethod reservationMethod;

    @Column(nullable = false)
    private boolean priceForEntireAcc;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<PriceList> prices = new HashSet<PriceList>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Host host;

    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private Address address;


    public void copyValues(Accommodation accommodation){
        this.title = accommodation.title;
        this.description = accommodation.description;
        this.minGuests = accommodation.minGuests;
        this.maxGuests = accommodation.maxGuests;
    }
}
