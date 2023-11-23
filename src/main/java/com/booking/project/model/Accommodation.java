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
    private Long id;
    private String title;
    private String description;
    @OneToOne(cascade = { CascadeType.ALL })
    private Address address;
    private Point location;
    private List<Amenities> amenities;
    private Long minGuests;
    private Long maxGuests;
    private AccomodationType type;
    private CancellationPolicy cancellationPolicy;
    private boolean isAvailableForReservation;
    private ReservationMethod reservationMethod;
    @ManyToOne
    private Host host;
    private boolean Accommodation;
    @OneToMany
    private Set<PriceList> prices = new HashSet<PriceList>();


    public void copyValues(Accommodation accommodation){
        this.title = accommodation.title;
        this.description = accommodation.description;
        this.minGuests = accommodation.minGuests;
        this.maxGuests = accommodation.maxGuests;
    }
}
