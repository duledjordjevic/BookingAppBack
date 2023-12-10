package com.booking.project.model;

import com.booking.project.dto.AccommodationDTO;
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

    @ElementCollection(targetClass = Amenities.class)
    @JoinTable(name = "amenities", joinColumns = @JoinColumn(name = "accommodationID"))
    @Enumerated(EnumType.STRING)
    private List<Amenities> amenities;
    @Column(columnDefinition="text", length=10485760)
    private String  images;

    @Column(nullable = false)
    private int minGuests;

    @Column(nullable = false)
    private int maxGuests;

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
    private boolean isPriceForEntireAcc;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<PriceList> prices = new HashSet<PriceList>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Host host;

    @OneToOne(cascade = { CascadeType.ALL })
    private Address address;


    public void copyValues(Accommodation accommodation){
        this.title = accommodation.title;
        this.description = accommodation.description;
        this.minGuests = accommodation.minGuests;
        this.maxGuests = accommodation.maxGuests;
    }

    public Accommodation(AccommodationDTO accommodationDTO){
        this.title = accommodationDTO.getTitle();
        this.description = accommodationDTO.getDescription();
        this.address = accommodationDTO.getAddress();
        this.amenities=  accommodationDTO.getAmenities();
//        this.images = accommodationDTO.getImages();
        this.minGuests = accommodationDTO.getMinGuest();
        this.maxGuests = accommodationDTO.getMaxGuest();
        this.type = accommodationDTO.getType();
        this.prices = accommodationDTO.getPrices();
        this.cancellationPolicy = accommodationDTO.getCancellationPolicy();
        this.isAvailableForReservation = accommodationDTO.isAvailableForReservation();
        this.reservationMethod = accommodationDTO.getReservationMethod();
        this.isPriceForEntireAcc = accommodationDTO.isPriceForEntireAcc();

    }

    public void copyValues(AccommodationDTO accommodationDTO){
        this.title = accommodationDTO.getTitle();
        this.description = accommodationDTO.getDescription();
        this.minGuests = accommodationDTO.getMinGuest();
        this.maxGuests = accommodationDTO.getMaxGuest();
    }
}
